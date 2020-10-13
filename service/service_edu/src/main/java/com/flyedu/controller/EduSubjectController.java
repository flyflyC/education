package com.flyedu.controller;


import com.flyedu.common.Result;
import com.flyedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-13
 */
@RestController
@RequestMapping("/eduSubject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    @PostMapping("/addSubjec")
    public Result SaveExcel(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return Result.ok();
    }
}

