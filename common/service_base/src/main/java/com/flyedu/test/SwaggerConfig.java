package com.flyedu.test;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Description
 * @ClassName swaggerConfig
 * @Author cai feifei
 * @date 2020.10.11 15:46
 * @Version
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 多人开发对个分组
     * @return
     */
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("fly");
    }

    /**
     * 配置Swagger的Docket的bean实例
     * @param environment
     * @return
     */
    @Bean
    public Docket docket(Environment environment){
        //设置要显示swagger的环境
        Profiles profiles=Profiles.of("dev","test");
        //获取项目环境
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable：false关闭swagger
                //.enable(flag)
                .groupName("flyfly")
                .select()
                //RequestHandlerSelectors，配置要扫描的接口方式：basePackage：指定要扫描的包
                //any()：扫描全部；none()：不扫描；withClassAnnotation:扫描类上的注解，参数是一个反射对象
                //withMethodAnnotation：扫描方法上的注解
                //.apis(RequestHandlerSelectors.basePackage("com.flyedu.controller"))
                //paths()：过滤路径
                .paths(Predicates.not(PathSelectors.regex("/admin/*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    /**
     * 配置swagger的apiInfo信息
     * @return
     */
    private ApiInfo apiInfo() {
        //作者信息
        Contact DEFAULT_CONTACT = new Contact("flyfly", "https://blog.csdn.net/qq_41510551", "1903203411@qq.com");
        return new ApiInfo("flyblog的Swagger Api文档",
                "在线教育",
                "v1.0",
                "urn:tos",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
