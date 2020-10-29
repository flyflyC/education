package com.flyedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @ClassName ServiceAclApplication
 * @Author cai feifei
 * @date 2020.10.21 16:59
 * @Version
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.flyedu","com.flyedu.mapper"})
public class ServiceAclApplication {
    public static void main(String [] args){
        SpringApplication.run(ServiceAclApplication.class,args);
    }
}
