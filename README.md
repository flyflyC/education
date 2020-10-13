# 在线教育项目

### day1
完成了讲师的CURD，及多条件查询，分页查询
——common：公共部分

————common_utils:
           
           Result:封装了统一的返回结果集

————service_base：service服务的基础统一配置

        1.SwaggerConfig：swagger配置
        2.GlobalExceptionHandler：统一异常处理
        3.MyMetaObjectHandler：自动填充
——service：服务

————service_edu：教育模块服务

        1、讲师管理
          （1）讲师列表
          （2）逻辑删除讲师
          （3）分页查询
          （4）多条件分页查询
          （5）添加讲师
          （6）通过id查询
          （7）通过id修改讲师信息


### day2
完成了讲师管理模块的后端前台模块

具体地址：[https://github.com/flyflyLJ/edu-vue](https://github.com/flyflyLJ/edu-vue)

### day3
- 集成了阿里云的对象存储OSS，完成了头像上传功能
- 集成了阿里云的easyExcel，测试了通过上传excel改变数据库中课程表的数据

————service_oos:阿里云对象存储服务
  
  完成了头像上传功能
  
————service_edu:添加了通过easyExcel上传excel文件添加课程功能


* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

