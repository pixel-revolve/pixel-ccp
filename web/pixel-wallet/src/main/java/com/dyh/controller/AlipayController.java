package com.dyh.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.config.AlipayConfig;
import com.dyh.entity.PWalletRecharge;
import com.dyh.feign.PUserFeignService;
import com.dyh.service.PWalletRechargeService;
import com.dyh.utils.RedisIdWorker;
import com.dyh.utils.UserHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.dyh.constant.WalletConstants.WALLET_RECHARGE_PREFIX;

@RestController
@RequestMapping("/api/alipay")
public class AlipayController {

	final static Logger log = LoggerFactory.getLogger(AlipayController.class);

	@Resource
	PWalletRechargeService pWalletRechargeService;

	@Resource
	PUserFeignService pUserFeignService;

	@Resource
	RedisIdWorker redisIdWorker;

	@Resource
	AlipayConfig alipayConfig;

	/**
	 * 得到用户支付宝充值链接
	 *
	 * @param amount 量
	 * @return {@link String}
	 * @throws Exception 异常
	 */
	@GetMapping( value = "/getUserRechargeLink/{amount}", produces = "text/html; charset=UTF-8")
	public String getUserRechargeLink(@PathVariable Long amount) throws Exception {
		// 1.获取用户id
		Long userId = UserHolder.getUser().getId();

		// 2.下单，创建一个recharge记录
		long walletRechargeId = redisIdWorker.nextId(WALLET_RECHARGE_PREFIX);
		PWalletRecharge pWalletRecharge=new PWalletRecharge();
		pWalletRecharge.setUserId(userId);
		pWalletRecharge.setAmount(amount);
		pWalletRecharge.setId(walletRechargeId);
		pWalletRechargeService.save(pWalletRecharge);
		// 2.1.更新用户余额
		pUserFeignService.rechargeById(userId,amount);

		// 2.加载支付宝各种证书
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppId(), alipayConfig.getMerchantPrivateKey(), "json", alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
		alipayRequest.setNotifyUrl(alipayConfig.getReturnUrl());

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = String.valueOf(walletRechargeId);
		//付款金额，必填
		double v = (pWalletRecharge.getAmount()) / 100.0;
		String total_amount = String.valueOf(v);
		//订单名称，必填
		String subject = "像素币充值";
		//商品描述，可空
		String body = "用户订购像素币总额：" + total_amount;

		// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
		String timeout_express = "1c";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		return alipayClient.pageExecute(alipayRequest).getBody();
	}

	/**
	 * 支付宝用户支付成功返回通知
	 *
	 * @param request  请求
	 * @param response 响应
	 * @return {@link R}
	 * @throws Exception 异常
	 */
	@RequestMapping(value = "/alipayReturnNotice")
	public R alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

		log.info("支付成功, 进入同步通知接口...");

		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (String name : requestParams.keySet()) {
			String valueStr=fromRequestParamsGetName(requestParams, name);;
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType()); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = getRequestParameter(request,"out_trade_no");
			//支付宝交易号
			String trade_no = getRequestParameter(request,"trade_no");
			//付款金额
			String total_amount = getRequestParameter(request,"total_amount");
			// 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
			pWalletRechargeService.updateOrderStatus(out_trade_no,trade_no,total_amount);

			//Orders order = orderService.getOrderById(out_trade_no);
			//Product product = productService.getProductById(order.getProductId());

			log.info("********************** 支付成功(支付宝同步通知) **********************");
			log.info("* 订单号: {}", out_trade_no);
			log.info("* 支付宝交易号: {}", trade_no);
			log.info("* 实付金额: {}", total_amount);
			log.info("* 购买产品: {}", "像素币");
			log.info("***************************************************************");

		}else {
			log.info("支付, 验签失败...");
		}

		return R.ok("成功");
	}

	@RequestMapping(value = "/alipayNotifyNotice")
	public R alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

		log.info("支付成功, 进入异步通知接口...");

		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (String name : requestParams.keySet()) {
			String valueStr=fromRequestParamsGetName(requestParams, name);
			//乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType()); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = getRequestParameter(request,"out_trade_no");
			//支付宝交易号
			String trade_no = getRequestParameter(request,"trade_no");
			//交易状态
			String trade_status = getRequestParameter(request,"trade_status");
			//付款金额
			String total_amount = getRequestParameter(request,"total_amount");

			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意：
				//付款完成后，支付宝系统发送该交易状态通知

				// 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
				pWalletRechargeService.updateOrderStatus(out_trade_no, trade_no, total_amount);

				// PWalletRecharge pWalletRecharge = pWalletRechargeService.getById(out_trade_no);
				// Product product = productService.getProductById(order.getProductId());

				log.info("********************** 支付成功(支付宝异步通知) **********************");
				log.info("* 订单号: {}", out_trade_no);
				log.info("* 支付宝交易号: {}", trade_no);
				log.info("* 实付金额: {}", total_amount);
				//log.info("* 购买产品: {}", product.getName());
				log.info("* 购买产品: {}", "像素币");
				log.info("***************************************************************");
			}
			log.info("支付成功...");

		}else {//验证失败
			log.info("支付, 验签失败...");
		}

		return R.ok("成功");
	}

	private String fromRequestParamsGetName(Map<String, String[]> requestParams, String name) {
		String[] values = requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		return valueStr;
	}

	private String getRequestParameter(HttpServletRequest request,String parameterName){
		return new String(request.getParameter(parameterName).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
	}
}
