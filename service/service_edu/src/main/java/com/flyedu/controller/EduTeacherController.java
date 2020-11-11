package com.flyedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.common.Result;
import com.flyedu.entity.EduTeacher;
import com.flyedu.entity.vo.TeacherVo;
import com.flyedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-11
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduService/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    @ApiOperation(value = "讲师列表")
    @GetMapping("/findAll")
    public Result findAllTeacher(){
        List<EduTeacher> teachers =  eduTeacherService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("teachers",teachers);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/delete/{id}")
    public Result removeTeacher(@PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error().data("id",id);
        }
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("/pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable("current")Integer current,
                                  @PathVariable("limit") Integer limit){
        //创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        //调用方法实现分页
        Page<EduTeacher> teacherPage = eduTeacherService.page(page);
        //获取总条数
        Long total = page.getTotal();
        //返回对象集合
        List<EduTeacher> teachers = page.getRecords();
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("rows",total);
        map.put("teachers", teachers);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "多条件查询")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable("current")Integer current,
                                       @PathVariable("limit") Integer limit,
                                       @RequestBody(required = false) TeacherVo teacherVo){
        //创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);


        Map<String,Object> map = eduTeacherService.getTeacherInfoList(page,teacherVo);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation(value = "通过id查询")
    @PostMapping("/getTeacherById/{id}")
    public Result getTeacherById(@PathVariable("id") String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("teacher",teacher);
        if (teacher!=null){
            return Result.ok().data(map);
        }else {
            return Result.error().message("不存在该讲师id："+id);
        }

    }
    @ApiOperation(value = "通过id修改讲师信息")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.updateById(teacher);
        Map<String,Object> map = new HashMap<>();
        map.put("teacher",teacher);
        System.out.println(flag);
        if (flag){
            return Result.ok().data(map);
        }else {
            return Result.error().data(map);
        }
    }
}

