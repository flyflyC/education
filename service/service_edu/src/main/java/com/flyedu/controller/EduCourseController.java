package com.flyedu.controller;


import com.flyedu.common.Result;
import com.flyedu.entity.vo.CourseInfoVo;
import com.flyedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    @ApiOperation(value = "添加课程信息")
    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.addCurseInfo(courseInfoVo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("courseId",id);
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
}

