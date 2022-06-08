package com.bnz.bdlpc;

import com.bnz.bdlpc.modules.RabbitCallback;
import org.springframework.beans.factory.annotation.Autowired;
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
