package com.flyedu.controller;

import com.flyedu.common.Result;
import com.flyedu.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @Description
 * @ClassName OssController
 * @Author cai feifei
 * @date 2020.10.12 23:34
 * @Version
 */
@Api(description = "oss服务")
@RestController
@RequestMapping("/eduOss")
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;

    @ApiOperation("头像上传")
    @PostMapping("/avatar")
    public Result uploadOosFile(MultipartFile file){
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url=ossService.uploadAvatar(file);
        HashMap<String, Object> map = new HashMap<>();
        map.put("url",url);
        return Result.ok().data(map);
    }
}
