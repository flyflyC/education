package com.flyedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.common.Result;
import com.flyedu.entity.CrmBanner;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 * 轮播图的管理员操作
 * @author cai fei fei
 * @since 2020-10-16
 */
@Api(description = "banner管理")
@RestController
@RequestMapping("/cmsService/bannerAdmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation(value = "分页查询banner")
    @GetMapping("/pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable Long page,@PathVariable Long limit){
        Page<CrmBanner> bannerPage = new Page<>();
        crmBannerService.page(bannerPage);
        List<CrmBanner> banners = bannerPage.getRecords();
        long total = bannerPage.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("banners",banners);
        map.put("total",total);

        return Result.ok().data(map);
    }

    @ApiOperation(value = "通过ID查询banner")
    @GetMapping("/selectBannerById/{id}")
    public Result selectBannerById(@PathVariable String id){
        CrmBanner byId = crmBannerService.getById(id);
        if(byId != null){
            throw new EduException(20001,"查询banner失败");
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("banner",byId);
            return Result.ok().data(map);
        }
    }

    @ApiOperation(value = "添加banner")
    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner){
        boolean save = crmBannerService.save(crmBanner);
        if(!save){
            throw new EduException(20001,"添加banner失败");
        }else {
            return Result.ok();
        }
    }

    @ApiOperation(value = "修改banner")
    @PostMapping("/updateBanner")
    public Result updateBanner(@RequestBody CrmBanner crmBanner){
        boolean updateById = crmBannerService.updateById(crmBanner);
        if(!updateById){
            throw new EduException(20001,"修改banner失败");
        }else {
            return Result.ok();
        }
    }

    @ApiOperation(value = "删除banner")
    @PostMapping("/deleteBanner/{id}")
    public Result deleteBannerById(@PathVariable String id){
        boolean deleteById = crmBannerService.removeById(id);
        if(!deleteById){
            throw new EduException(20001,"删除banner失败");
        }else {
            return Result.ok();
        }
    }
}

