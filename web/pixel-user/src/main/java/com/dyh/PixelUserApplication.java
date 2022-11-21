package com.dyh;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class PixelUserApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(PixelUserApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Local访问网址: \t\thttp://localhost:" + port + "\n\t" +
                "----------------------------------------------------------");
    }
}
