package com.flyedu.controller;


import com.flyedu.common.Result;
import com.flyedu.entity.EduChapter;
import com.flyedu.entity.EduVideo;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
@Api(description = "章节小节模块")
@RestController
@RequestMapping("/eduService/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @ApiOperation(value = "添加小节")
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        boolean save = videoService.save(eduVideo);
        if (!save){
            throw new EduException(20001,"添加章节失败");
        }
        return Result.ok();
    }

    @ApiOperation(value = "通过id查询小节")
    @GetMapping("/getVideoById/{id}")
    public Result getVideoById(@PathVariable("id") String id){
        EduVideo video = videoService.getById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("video",video);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "修改小节")
    @PostMapping("/updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return Result.ok();
    }

    @ApiOperation(value = "删除小节")
    @DeleteMapping("/deleteVideo/{id}")
    public Result deleteVideo(@PathVariable("id") String id){
        boolean b = videoService.removeById(id);
        if (!b){
            throw new EduException(20001,"删除小节失败");
        }
        return Result.ok();
    }

}

