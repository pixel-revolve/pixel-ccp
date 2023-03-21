package com.dyh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

	private String appId;

	private String merchantPrivateKey;

	private String alipayPublicKey;

	private String notifyUrl;

	private String returnUrl;

	private String signType;

	private String charset;

	private String gatewayUrl;

}
