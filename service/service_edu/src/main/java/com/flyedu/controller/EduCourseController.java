package com.flyedu.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.common.Result;
import com.flyedu.entity.EduCourse;
import com.flyedu.entity.vo.CourseInfoVo;
import com.flyedu.entity.vo.CoursePublishVo;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
@Api(description ="课程管理")
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    @ApiOperation(value = "课程列表")
    @GetMapping("/findAll")
    public Result findAllCourse(){
        List<EduCourse> courses =  eduCourseService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("courses",courses);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "逻辑删除课程")
    @DeleteMapping("/delete/{courseId}")
    public Result removeCourse(@PathVariable String courseId) {
        boolean flag = eduCourseService.removeCourseById(courseId);
        if (flag) {
            String id = courseId;
            eduCourseService.removeById(id);
            return Result.ok();
        } else {
            return Result.error().data("id",courseId);
        }
    }

    @ApiOperation(value = "添加课程信息")
    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.addCurseInfo(courseInfoVo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("courseId",id);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("/pageCourse/{current}/{limit}")
    public Result pageListCourse(@PathVariable("current")Integer current,
                                  @PathVariable("limit") Integer limit){
        //创建page对象
        Page<EduCourse> page = new Page<>(current,limit);
        //调用方法实现分页
        Page<EduCourse> coursePage = eduCourseService.page(page);
        //获取总条数
        Long total = page.getTotal();
        //返回对象集合
        List<EduCourse> courses = page.getRecords();
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("rows",total);
        map.put("courses", courses);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "多条件查询")
    @PostMapping("/pageCourseCondition/{current}/{limit}")
    public Result pageCourseCondition(@PathVariable("current")Integer current,
                                       @PathVariable("limit") Integer limit,
                                       @RequestBody(required = false) EduCourse course){

        //创建page对象
        Page<EduCourse> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //多条件组合查询,动态sql
        String title = course.getTitle();
        String status = course.getStatus();
        System.out.println("title=="+title+"===status==="+status);
        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)){
            wrapper.eq(true,"status",status);
        }
        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现分页
        eduCourseService.page(page,wrapper);
        //获取总条数
        Long total = page.getTotal();
        //返回对象集合
        List<EduCourse> courses = page.getRecords();
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("courses", courses);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "回显课程信息")
    @GetMapping("/getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable("courseId") String courseId){
        CourseInfoVo courseInfo =  eduCourseService.getCourseInfo(courseId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("courseInfo",courseInfo);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.updateCourseInfo(courseInfoVo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("courseId",id);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "确认课程信息")
    @GetMapping("/getPublishCourseInfo/{courseId}")
    public Result getPublishCourseInfo(@PathVariable("courseId") String courseId){
        CoursePublishVo publishCourseInfo = eduCourseService.getPublishCourseInfo(courseId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("publishCourseInfo",publishCourseInfo);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "发布课程信息")
    @PostMapping("/publishCourse/{id}")
    public Result publishCourse(@PathVariable String id){
        System.out.println(id);
        EduCourse course = new EduCourse();
        EduCourse eduCourse = eduCourseService.getById(id);
        //course.setId(id);
        System.out.println(course.toString());
        //eduCourse.setStatus("Normal");
        String s="Normal";
        eduCourse.setStatus(s);
        System.out.println(course.toString());
        boolean b = eduCourseService.updateById(eduCourse);
        System.out.println(b);
        if (!b){
            throw new EduException(20001,"发布课程失败");
        }
        return Result.ok();
    }
}

