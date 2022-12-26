package com.dyh.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OSSConfig implements InitializingBean {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    @Value("${pixel-oss.endpoint}")
    private String endpoint;

    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    @Value("${pixel-oss.accessKeyId}")
    private String accessKeyId;

    @Value("${pixel-oss.accessKeySecret}")
    private String accessKeySecret;

    // 填写Bucket名称，例如examplebucket。
    @Value("${pixel-oss.bucketName}")
    private String bucketName;
    @Value("${pixel-oss.url}")
    private String url;

    public static String ENDPOINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    public static String URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
        URL = url;
    }
}
