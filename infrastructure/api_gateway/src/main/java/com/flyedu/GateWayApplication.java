package com.flyedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @ClassName GateWayApplicayion
 * @Author cai feifei
 * @date 2020.10.21 11:11
 * @Version
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication {
    public static void main(String [] args){
        SpringApplication.run(GateWayApplication.class,args);
    }
}
