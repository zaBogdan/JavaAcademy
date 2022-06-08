package com.bnz.bdlpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BDLPCApplication {

    public static void main(String[] args) {
        SpringApplication.run(BDLPCApplication.class, args);
    }

}
