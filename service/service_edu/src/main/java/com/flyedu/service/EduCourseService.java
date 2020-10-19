package com.flyedu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flyedu.entity.vo.frontvo.CourseFrontVo;
import com.flyedu.entity.vo.frontvo.CourseWebVo;
import com.flyedu.entity.vo.CourseInfoVo;
import com.flyedu.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据id降序查询前八条课程数据
     * @param courseQueryWrapper
     * @return
     */
    List<EduCourse> getCourseList(QueryWrapper<EduCourse> courseQueryWrapper);

    /**
     * 前台数据展示：多条件分页查询
     * @param pageCourse
     * @param courseFrontVo
     * @return
     */
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    /**
     * 根据课程id查询课程详情
     * @param courseId
     * @return
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
