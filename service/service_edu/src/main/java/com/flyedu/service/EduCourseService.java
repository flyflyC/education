package com.flyedu.service;

import com.flyedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flyedu.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息接口
     * @param courseInfoVo
     */
    String addCurseInfo(CourseInfoVo courseInfoVo);

    /**
     * 回显课程信息接口
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    String updateCourseInfo(CourseInfoVo courseInfoVo);
}
