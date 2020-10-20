package com.flyedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @ClassName UcentenApplication
 * @Author cai feifei
 * @date 2020.10.17 21:24
 * @Version
 */
@SpringBootApplication
@ComponentScan({"com.flyedu","com.flyedu.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
public class UcentenApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcentenApplication.class,args);
    }

}
