package com.dyh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 线程池配置参数
 * @author pixel-revolve
 * @date 2023/04/25
 */
@Data
@ConfigurationProperties(prefix = "pixel.thread")
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;

}
