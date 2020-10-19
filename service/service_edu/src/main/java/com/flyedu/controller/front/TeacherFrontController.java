package com.flyedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.common.Result;
import com.flyedu.entity.EduCourse;
import com.flyedu.entity.EduTeacher;
import com.flyedu.service.EduCourseService;
import com.flyedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 前台讲师展示界面
 * @ClassName TeacherFrontController
 * @Author cai feifei
 * @date 2020.10.18 20:32
 * @Version
 */
@Api(description = "前台讲师管理")
@RestController
@RequestMapping("/eduService/indexFront")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "分页查询")
    @PostMapping("/pageTeacher/{current}/{limit}")
    public Result pageTeacherList(@PathVariable("current")Integer current,
                                  @PathVariable("limit") Integer limit){
        //创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        //调用方法实现分页
        Map<String,Object> map = teacherService.getTeacherFrontList(page);

        return Result.ok().data(map);
    }

    @ApiOperation(value = "获取讲师的课程信息")
    @GetMapping("/getCourseInfo/{teacherId}")
    public Result getCourseInfo(@PathVariable String teacherId){
        //通过讲师id获取讲师信息
        EduTeacher teacher = teacherService.getById(teacherId);
        //通过讲师id获取讲师课程信息
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacher",teacher);
        map.put("courseList",courseList);

        return Result.ok().data(map);
    }
}
