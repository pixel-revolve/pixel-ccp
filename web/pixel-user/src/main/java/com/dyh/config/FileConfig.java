package com.dyh.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
public class FileConfig {
    @Value("${file.upload-path}")
    private String uploadPath = "";

    @Value("${file.download-path}")
    private String downloadPath = "";

    @Value("${file.file-type-array}")
    private String[] fileTypeArray;

    @Value("${file.max-file-size}")
    private int maxFileSize;
}
