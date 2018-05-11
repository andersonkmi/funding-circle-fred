package com.fundingcircle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FredApplication {
    public static void main(String[] args) {
        SpringApplication.run(FredApplication.class, args);
    }
}
