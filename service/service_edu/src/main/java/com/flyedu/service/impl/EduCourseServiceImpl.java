package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.EduCourse;
import com.flyedu.entity.EduCourseDescription;
import com.flyedu.entity.vo.CourseInfoVo;
import com.flyedu.entity.vo.CoursePublishVo;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.mapper.EduCourseMapper;
import com.flyedu.service.EduChapterService;
import com.flyedu.service.EduCourseDescriptionService;
import com.flyedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    EduVideoService eduVideoService;

    @Autowired
    EduChapterService chapterService;
    /**
     * 向课程表（edu_course）添加课程实现方法
     * @param courseInfoVo
     */
    @Override
    public String addCurseInfo(CourseInfoVo courseInfoVo) {
        //1.将courseInfoVo中的信息添加入edu_course表中
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        System.out.println(eduCourse.toString());

        int insert = baseMapper.insert(eduCourse);
        if (insert<=0){
            throw new EduException(20001,"添加课程信息失败！");
        }
        //获取课程id，使课程id等于课程简介id
        String cid = eduCourse.getId();
        System.out.println(cid);

        //2.向课程描述表（edu_course_description）添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if (! save){
            throw new EduException(20001,"添加课程简介失败！");
        }
        return cid;
    }

    /**
     * 回显信息实现类
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfo);
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        courseInfo.setDescription(description.getDescription());
        return courseInfo;
    }

    @Override
    public String updateCourseInfo(CourseInfoVo courseInfoVo) {
        //获取到课程信息里面的课程id
        String cid = courseInfoVo.getSubjectId();
        //根据课程id查询课程信息和描述
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription courseDescription = new EduCourseDescription();
        //将传入的修改信息进行修改
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        //将修改的信息插入表中
        int i = baseMapper.updateById(eduCourse);
        if (i<=0){
            throw new EduException(20001,"修改课程表出错");
        }
        boolean b = eduCourseDescriptionService.updateById(courseDescription);
        if (!b){
            throw new EduException(20001,"修改课程表出错");
        }
        return cid;
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    /**
     * 根据课程id删除所有表里面和该课程相关的内容
     * @param courseId
     * @return
     */
    @Override
    public boolean removeCourseById(String courseId) {

         boolean isVideo = eduVideoService.removeVideoById(courseId);
         if (!isVideo){
             throw new EduException(20001,"删除小节失败！");
         }
         boolean isChapter =chapterService.removeChapterById(courseId);
         if (!isChapter){
             throw new EduException(20001,"删除章节失败！");
         }
        return true;
    }

    @Cacheable(key = "'courseList'",value = "courses")
    @Override
    public List<EduCourse> getCourseList(QueryWrapper<EduCourse> courseQueryWrapper) {
        List<EduCourse> courses = baseMapper.selectList(courseQueryWrapper);
        return courses;
    }
}
