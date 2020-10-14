package com.flyedu.controller;


import com.flyedu.common.Result;
import com.flyedu.entity.vo.subject.OneSubjectVo;
import com.flyedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-13
 */
@Api(description ="课程科目管理")
@RestController
@RequestMapping("/eduService/eduSubject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    @ApiOperation(value = "添加课程科目")
    @PostMapping("/addSubject")
    public Result saveExcel(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return Result.ok();
    }

    /**
     * 树形返回课程
     * @return
     */
    @ApiOperation(value = "获取课程树形菜单")
    @GetMapping("getAllSubject")
    public Result getAll(){
        List<OneSubjectVo> list = eduSubjectService.getAllOneTwoSubject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        return Result.ok().data(map);
    }
}

