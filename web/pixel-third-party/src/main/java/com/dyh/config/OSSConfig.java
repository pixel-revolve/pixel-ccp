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
    private String accessKeyId = "LTAI5tBCWcPvQfq3BfiMR2bs";

    @Value("${pixel-oss.accessKeySecret}")
    private String accessKeySecret = "hMKnXp2sJZwhJhwLbMes4mws2gGa9z";

    // 填写Bucket名称，例如examplebucket。
    @Value("${pixel-oss.bucketName}")
    private String bucketName = "pixel-ccp";

    // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
    // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
    @Value("${pixel-oss.filePath}")
    private String filePath = "E:\\oss-pixel\\avatar.jpg";

    public static String ENDPOINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_PATH;


    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
        FILE_PATH = filePath;
    }
}
