package com.flyedu.service;

import com.flyedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flyedu.entity.vo.CourseInfoVo;
import com.flyedu.entity.vo.CoursePublishVo;

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

    /**
     * 查询提交时的课程信息
     * @param courseId
     * @return
     */
    CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 根据课程id删除所有表里面和该课程相关的内容
     * @param courseId
     * @return
     */
    boolean removeCourseById(String courseId);
}
