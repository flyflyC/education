package com.flyedu.controller;


import com.flyedu.client.UcentenClient;
import com.flyedu.common.Result;
import com.flyedu.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
@Api(description = "统计分析")
@RestController
@RequestMapping("/staService/statistics")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @ApiOperation(value = "统计一天的注册人数")
    @GetMapping("/registerCount/{day}")
    public Result getRegisterCount(@PathVariable String day){
        System.out.println(day);
        int count = statisticsDailyService.countRegister(day);
        System.out.println(count);
        HashMap<String, Object> map = new HashMap<>();
        map.put("countRegister",count);
        return Result.ok().data(map);
    }
}

