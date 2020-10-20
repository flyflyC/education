package com.flyedu.client;

import com.flyedu.commonvo.CourseWebOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description 远程调用edu服务
 * @ClassName eduClient
 * @Author cai feifei
 * @date 2020.10.20 09:54
 * @Version
 */
@FeignClient(name = "service-edu",fallback = EduFileDegradeFeignClient.class)
@Component
public interface EduClient {
    /**
     * 通过远程调用edu服务获取课程信息
     * @param courseId
     * @return
     */
    @PostMapping("/eduService/course/getCourseInfoToOrder/{courseId}")
    public CourseWebOrderVo getCourseInfoToOrder(@PathVariable String courseId);
}
