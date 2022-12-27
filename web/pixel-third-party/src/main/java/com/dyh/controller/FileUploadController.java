package com.dyh.controller;

import cn.hutool.core.lang.UUID;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.config.OSSConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileUploadController {

    @PostMapping("/upload")
    public R upload(@RequestParam(value = "uploadFile", required = false) MultipartFile multiFile) throws IOException {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = OSSConfig.ENDPOINT;

        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = OSSConfig.ACCESS_KEY_ID;
        String accessKeySecret = OSSConfig.ACCESS_KEY_SECRET;

        // 填写Bucket名称，例如examplebucket。
        String bucketName = OSSConfig.BUCKET_NAME;

        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        Date date = new Date();
        String strDateFormat = "yyyy/MM/dd/HH-ss-mm";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String pri = sdf.format(date);
        String random = UUID.randomUUID().toString();
        String objectName = "avatar/"+pri+"/" + random + ".jpg";
        InputStream inputStream = multiFile.getInputStream();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            return R.failed("文件上传失败");
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            return R.failed("文件上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        // https://pixel-ccp.oss-cn-hangzhou.aliyuncs.com/avatar/2022/11/26/15/05/49/OIP-C2.jpg
        String url = OSSConfig.URL + objectName;
        return R.ok(url);
    }

}
