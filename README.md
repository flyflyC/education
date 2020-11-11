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


### day4
- 完成了课程分类管理模块
    
- 完成了课程管理模块的添加课程信息功能的第一个步骤及第二个步骤的章节部分

            1、CourseInfoVo————课程信息视图
            2、vo.subject:————树形显示课程
                    OneSubjectVo:课程一级分类
                    TwoSubjectVo：课程二级分类
            3、vo.chapter————章节管理视图
                    Chapter：章节
                    VideVo：小节
            4、EduCourseController————课程管理模块
            5、EduChapterController————课程章节
            5、EduVideoController————章节小节模块管理
            
问题：多级联动问题，树形展示问题
                    
### day5

- 完成了课程信息确认功能即添加课程的第三个步骤

- 完成了课程信息的发布功能

发布即将课程的status字段改为Normal
- 完成了小节中上传视频功能

使用了阿里云的视频点播服务：1、登录阿里云，开通视频点播VOD服务；

2、通过之前使用oss产生的keyId和Keysecret

3、上传视频及获取视频号

- 完成了删除小节时把阿里云视频点播服务里面的视频删除
    
    service_edu模块需要调用service_vod的删除视频功能，
    两个功能处于两个不同的服务里面因此需要使用进行微服务注册（spring cloud很多技术的集合）
    
    使用nacos进行微服务的注册：使用
        
        1、添加依赖：服务注册和服务调用依赖
        2、在服务的配置文件中配置nacos端口，启动入口添加注解@EnableDiscoveryClient
        3、在需要调用的服务（消费者）中，添加写调取服务的接口，@FeignClient("服务名")
        在接口在方法配置 @XXXMapping("服务的全路径")
         @DeleteMapping("/eduVod/video/removeAliyunVideo/{id}")
            public Result deleteAliyunVideo(@PathVariable("id") String id);


### day6

- 添加熔断机制：

 1、添加依赖：
    
    <!--使用ribbon做负载均衡-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
                <version>2.1.1.RELEASE</version>
            </dependency>
            <!--hystrix(熔断机制依赖）主要使用 @HystrixCommand-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-netflix-hystrix</artifactId>
                <version>2.1.1.RELEASE</version>
            </dependency>

 2、配置熔断机制（在消费端）
 
            #开启熔断机制
            feign:
              hystrix:
                enabled: true

 3、实现服务的接口
 
 4、在接口上的@FeignClient注解上加上：@FeignClient(name = "服务名",fallback = 实现类名.class)
 
 - 完成banner管理，及讲师课程的前几条数据的前端展示功能
 

问题：

1：使用逻辑删除字段后需要在新增一条数据时，给逻辑删除字段加默认值，否则修改时，不能修改成功

2：取消logback日志后：启动报错：`Failed to bind properties under 'logging.level' to java.util.Map<java.lang.String, org.springframework.boot.logging.LogLevel>`

解决：
`#设置日志级别

    logging:
      level:
        root: info`

3: No fallback instance of type class com.flyedu.client.VodFileDegradeFeignClient found for feign client service-vod
报错没有找到熔断机制的类：错误出现原因，添加熔断机制后，没有把实现熔断机制的方法交给spring容器管理
解决：加上注解：@Component

4:Data source rejected establishment of connection,  message from server: "Too many connections"
超过数据库连接数：解决方法：max_connections设置的大一些

## day7 

1、给首页配置Redis缓存

- 下载安装Redis
- 在yml文件中配置
            #redis配置
              #spring.redis.host=(192.168.44.132)redis地址
            spring
              redis:
                host: 127.0.0.1
                port: 6379
                database: 0
                timeout: 1800000
                lettuce:
                  pool:
                    max-active: 20
                    max-wait: -1
                    #最大阻塞等待时间(负数表示没限制)
                    max-idle: 5
                    min-idle: 0

- 编写redis的配置类 education\common\service_base\src\main\java\com\flyedu\config\RedisConfig.java

- 在需要添加缓存的service实现类的接口上添加注解 @Cacheable(key = "'xxx'",value = "xxx")

2、短信验证实现：service_msm：使用阿里云的短信服务

3、用户登录注册功能及单点登录实现

- 单点登录实现方式：（1、session广播机制；2、cookie+redis实现；3、使用token实现）
        本项目使用token实现：education-common-common_utils-src-main-java-com-flyedu-common-JwtUtils.java
        JWT：JSON Web Token：一个轻巧的规范。这个规范允许我们使用JWT在用户和服务器之间传递安全可靠的信息

问题：
	TypeError: Failed to execute 'fetch' on 'Window': Request with GET/HEAD method cannot have body.
	传值为对象时，需要使用PostMapper提交，否则报错
	
## day8

1、登录注册：手机号登录、微信登录
   - （需要申请微信的开发者——需要为企业）这里是有尚硅谷的谷粒学院的微信服务；通过请求到二维码后，用户扫码后，
   1 获取code值，临时票据，类似于验证码
   2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid ："https://api.weixin.qq.com/sns/oauth2/access_token" +
"?appid=%s" +  "&secret=%s" +  "&code=%s" +  "&grant_type=authorization_code";
   3、请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid; 使用httpclient发送请求，得到返回结果（返回结果为json字符串，可以把其转换为map便于取值，传值）
   4拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息，访问微信的资源服务器，获取用户信息
   
2、讲师前台展示列表

## day9

1、讲师分页查询

2、课程详情功能接口（课程多条件查询）

2、视频播放获取播放凭证接口

3、课程评论功能（service_edu模块远程调用service_ucenten服务的获取用户ID接口）

问题：报错启动熔断机制，查询超时；原因：远程调用时，没有把路径名字写全。


## day10

1、微信支付功能完成（service_order)
    - 创建订单
    - 通过商户id调用微信接口，传递支付参数，获取支付二维码
    - 扫码支付，支付时，每3秒调取后端支付状态，支付成功后结束
    
2、统计分析————生成统计信息接口完成(service_statistics)

问题：各个服务之间调取不同，开始时，通过查询调取服务的路径和熔断机制查询问题所在，后面一直报超时，最后通过访问nacos的网页看各个服务的状态找到问题所在

## day11

1、统计接口完善

2、图表功能结合定时任务，每天0点、7点、17点统计一次数据

3、完成网关服务：F:\IdeaProject\education\infrastructure\api_gateway

4、菜单查询

问题：网关服务，报各种错：大概问题错误：1、gateway整合了：spring-boot-starter-web；spring-boot-starter-webflux；，如果父pom或者自身含有这两个就会报错
2、Springboot和springcloud整合的版本问题也会导致问题报错


## day12

1、service_blog:博客模块
博客模块完成：包括博客详情，博客列表、博客展示、博客修改、博客编辑等内容

## 2020-11-11

博客评论功能，及解决博客修改功能的bug