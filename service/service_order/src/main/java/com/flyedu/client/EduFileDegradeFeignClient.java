package com.flyedu.client;

import com.flyedu.commonvo.CourseWebOrderVo;
import org.springframework.stereotype.Component;


/**
 * @Description
 * @ClassName EduFileDegradeFeignClient
 * @Author cai feifei
 * @date 2020.10.20 09:57
 * @Version
 */
@Component
public class EduFileDegradeFeignClient implements EduClient{
    @Override
    public CourseWebOrderVo getCourseInfoToOrder(String courseId) {
        System.out.println("调用edu服务失败");
        return null;
    }
}
