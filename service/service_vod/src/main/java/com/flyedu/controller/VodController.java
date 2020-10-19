package com.flyedu.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.flyedu.common.Result;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.VodService;
import com.flyedu.utils.InitVodObject;
import com.flyedu.utils.VodUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @ClassName VodController
 * @Author cai feifei
 * @date 2020.10.15 15:10
 * @Version
 */
@RestController
@CrossOrigin
@Api(description = "视频点播")
@RequestMapping("/eduVod/video")
public class VodController {

    @Autowired(required = false)
    private VodService vodService;


    @ApiOperation(value = "上传视频到阿里云")
    @PostMapping("/uploadAliyunVideo")
    public Result uploadAliyunVideo(MultipartFile file) throws IOException {
        String videoId = vodService.uploadVideo(file);
        HashMap<String, Object> map = new HashMap<>();
        map.put("videoId",videoId);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "删除阿里云上的视频")
    @DeleteMapping("/removeAliyunVideo/{id}")
    public Result deleteAliyunVideo(@PathVariable String id) throws IOException {
        System.out.println(id);
        try {
            //初始化视频点播对象
            DefaultAcsClient initVodClient = InitVodObject.initVodClient(VodUtil.KEY_ID, VodUtil.KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest videoRequest = new DeleteVideoRequest();
            //向request对象传入id
            videoRequest.setVideoIds(id);
            //调用初始化对象删除id
            initVodClient.getAcsResponse(videoRequest);
            return Result.ok();
        }catch (Exception e){
            throw new EduException(20001,"删除失败");
        }
    }
    @ApiOperation(value = "删除阿里云上的多个视频")
    @DeleteMapping("/removeAlyVideoBatch")
    public Result deleteAlyVideoBatch(@RequestParam("videoList") List<String> videoList) {
        vodService.deleteAlyVideoBach(videoList);
        return Result.ok();
    }

    @ApiOperation(value = "获取视频播放凭证")
    @GetMapping("/getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id){
        try {
            //初始化视频点播对象
            DefaultAcsClient initVodClient = InitVodObject.initVodClient(VodUtil.KEY_ID, VodUtil.KEY_SECRET);
            System.out.println(initVodClient);
            //创建获取凭证的request对象及response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request中传递视频id
            request.setVideoId(id);
            //调用方法获取凭证
            GetVideoPlayAuthResponse response = initVodClient.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            System.out.println(playAuth);
            HashMap<String, Object> map = new HashMap<>();
            map.put("playAuth",playAuth);
            return Result.ok().data(map);
        }catch (Exception e){
            throw new EduException(20001,"获取凭证失败");
        }
    }
}
