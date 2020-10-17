package com.flyedu.controller;

import com.flyedu.common.Result;
import com.flyedu.entity.CrmBanner;
import com.flyedu.service.CrmBannerService;
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
 * @Description 前台展示轮播图
 * @ClassName BannerFrontController
 * @Author cai feifei
 * @date 2020.10.16 18:31
 * @Version
 */
@Api(description = "banner前台展示")
@RestController
@RequestMapping("/cmsService/bannerFront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "查询所有banner")
    @GetMapping("/getAllBanner")
    public Result getAllBanner(){
        List<CrmBanner> list = bannerService.seletBannerList();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        return Result.ok().data(map);
    }
}
