package com.bnz.services.plsqlcompiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PlsqlCompilerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlsqlCompilerServiceApplication.class, args);
    }

}
