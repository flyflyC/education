package com.flyedu.mapper;

import com.flyedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyedu.entity.vo.frontvo.CourseWebVo;
import com.flyedu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 课程提交确定信息
     * @param courseId
     * @return
     */
    public CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 根据课程id查询课程详情
     * @param courseId
     * @return
     */
    public CourseWebVo getCourseInfoById(String courseId);
}
