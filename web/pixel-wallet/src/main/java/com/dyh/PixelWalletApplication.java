package com.dyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PixelWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(PixelWalletApplication.class, args);
    }

}
