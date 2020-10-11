package com.flyedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @ClassName EduApplication
 * @Author cai feifei
 * @date 2020.10.11 15:23
 * @Version
 */
@SpringBootApplication
@ComponentScan({"com.flyedu","com.flyedu.mapper"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
