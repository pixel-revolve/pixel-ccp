package com.dyh.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class FileDownloadUtil {
    /**
     * 下载文件到服务器
     *
     * @param downloadUrl      要下载的文件的地址
     * @param downloadPath     服务器上存储的文件路径
     * @param downloadFileName 服务器上存储的文件名称
     * @return
     */
    public static boolean downloadToServer(String downloadUrl, String downloadPath, String downloadFileName) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        boolean flag = false;
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            bis = new BufferedInputStream(connection.getInputStream());
            File file = new File(downloadPath);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                if (!mkdirs) {
                    log.error("创建文件目录失败");
                    return false;
                }
            }
            String filePathName = downloadPath + File.separator + downloadFileName;
            byte[] buf = new byte[1024];
            int size;
            fos = new FileOutputStream(filePathName);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            flag = true;
            log.info("文件下载成功,文件路径[" + filePathName + "]");
            flag = true;
        } catch (Exception e) {
            log.error("下载文件异常", e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.error("关流异常", e);
                e.printStackTrace();
            }
        }
        return flag;
    }

}
