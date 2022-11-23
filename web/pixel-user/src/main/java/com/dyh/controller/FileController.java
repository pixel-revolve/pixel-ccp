package com.dyh.controller;

import com.alibaba.fastjson.JSONObject;
import com.dyh.config.FileConfig;
import com.dyh.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileConfig fileConfig;

    @ResponseBody
    @RequestMapping(value = "/upload")
    public String uploadToServer2(@RequestParam(value = "uploadFile", required = false) MultipartFile multiFile) {
        JSONObject json = new JSONObject();
        try {
            Pair<Boolean, String> pair = checkFile(multiFile);
            if (!pair.getLeft()) {
                json.put("msg", pair.getRight());
                return json.toJSONString();
            }
            boolean b = FileUploadUtil.uploadToServer(multiFile, fileConfig.getUploadPath(), multiFile.getOriginalFilename());
            json.put("msg", b ? "上传成功" : "上传失败");
            return json.toJSONString();
        } catch (Exception e) {
            log.error("系统异常e:", e);
            json.put("msg", "上传失败");
            return json.toJSONString();
        }
    }

    public Pair<Boolean, String> checkFile(MultipartFile multiFile) {
        if (multiFile.isEmpty()) {
            return Pair.of(false, "文件为空");
        }
        //获取
        String filename = multiFile.getOriginalFilename();
        String contentType = multiFile.getContentType();
        if (StringUtils.isBlank(filename)) {
            return Pair.of(false, "文件名为空");
        }
        long size = multiFile.getSize();//字节
        log.info("收到的请求文件信息：原生文件名：{},文件类型：{},文件大小：{}", filename, contentType, size);
        //获取文件后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        //判断配置的文件列表里是否支持该文件类型
        if (!ArrayUtils.contains(fileConfig.getFileTypeArray(), suffix)) {
            return Pair.of(false, "不支持该类型文件上传");
        }
        double fileSize = size / 1024.0;//单位kb
        if (fileSize > fileConfig.getMaxFileSize()) {
            return Pair.of(false, "文件大小超过限制");
        }
        return Pair.of(true, "验证通过");
    }

    @RequestMapping(value = "/download")
    public void downloadToClient(HttpServletRequest request, HttpServletResponse response) {
        String filePath = fileConfig.getDownloadPath();
        String fileName = "download.pdf";
        String filePathName = filePath + File.separator + fileName;
        BufferedInputStream bins = null;
        BufferedOutputStream bouts = null;
        try {
            //同一个窗口下载多次，清除空白流
            response.reset();
            File file = new File(filePathName);
            if (!file.exists()) {
                log.error("要下载的文件不存在：{}", filePathName);
                return;
            }
            bins = new BufferedInputStream(new FileInputStream(filePathName));
            bouts = new BufferedOutputStream(response.getOutputStream());
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            // 如果是火狐浏览器
            if (userAgent.contains("firefox")) {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            //设置发送到客户端的响应的内容类型
            response.setContentType("application/download");
            //指定客户端下载的文件的名称
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            int len;
            byte[] bytes = new byte[1024];
            while ((len = bins.read(bytes)) != -1) {
                bouts.write(bytes, 0, len);
            }
            //刷新流
            bouts.flush();
            log.info("下载完成");
        } catch (IOException e) {
            log.error("下载文件异常:{}", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bouts != null) {
                    bouts.close();
                }
                if (bins != null) {
                    bins.close();
                }
            } catch (IOException e) {
                log.error("关闭流异常", e);
                e.printStackTrace();
            }
        }
    }

}
