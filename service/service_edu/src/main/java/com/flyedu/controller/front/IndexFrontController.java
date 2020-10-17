package com.flyedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.common.Result;
import com.flyedu.entity.EduCourse;
import com.flyedu.entity.EduTeacher;
import com.flyedu.service.EduCourseService;
import com.flyedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @ClassName IndexFrontController
 * @Author cai feifei
 * @date 2020.10.16 19:07
 * @Version
 */
@RestController
@Api(description = "前台展示")
@RequestMapping("/eduService/indexFront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "首页展示")
    @GetMapping("/index")
    public Result index(){
        //根据id降序查询前八条课程数据
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");

        List<EduCourse> eduCourses = courseService.getCourseList(courseQueryWrapper);

        //根据id降序查询前4条讲师数据
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");

        List<EduTeacher> eduTeachers = teacherService.getTeacherList(teacherQueryWrapper);

        HashMap<String, Object> map = new HashMap<>();
        map.put("courses",eduCourses);
        map.put("teachers",eduTeachers);

        return Result.ok().data(map);
    }

}
