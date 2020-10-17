package com.flyedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @ClassName CmsApplication
 * @Author cai feifei
 * @date 2020.10.16 17:35
 * @Version
 */
@SpringBootApplication
@ComponentScan({"com.flyedu","com.flyedu.mapper"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
