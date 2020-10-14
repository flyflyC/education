package com.flyedu.controller;


import com.flyedu.common.Result;
import com.flyedu.entity.EduChapter;
import com.flyedu.entity.vo.chapter.ChapterVo;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
@RestController
@RequestMapping("/eduService/chapter")
@CrossOrigin
@Api(description ="课程章节")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    /**
     * 根据课程id查询所有章节
     * @param courseId
     * @return
     */
    @ApiOperation(value = "根据课程id查询所有章节")
    @GetMapping("/getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("allChapterVideo",list);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "添加章节")
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        boolean save = chapterService.save(eduChapter);
        if (!save){
            throw new EduException(20001,"添加章节失败");
        }
        return Result.ok();
    }

    @ApiOperation(value = "通过id查询章节")
    @GetMapping("/getChapterById/{id}")
    public Result getChapterById(@PathVariable("id") String id){
        EduChapter chapter = chapterService.getById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("chapter",chapter);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "修改章节")
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return Result.ok();
    }

    @ApiOperation(value = "删除章节")
    @DeleteMapping("/deleteChapter/{id}")
    public Result deleteChapter(@PathVariable("id") String id){
        boolean b = chapterService.deleteChapter(id);
        if (!b){
            throw new EduException(20001,"添加章节失败");
        }
        return Result.ok();
    }

}

