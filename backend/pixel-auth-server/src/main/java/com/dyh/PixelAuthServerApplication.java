package com.dyh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PixelAuthServerApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(PixelAuthServerApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Local访问网址: \t\thttp://localhost:" + port + "\n\t" +
                "----------------------------------------------------------");
    }
}
