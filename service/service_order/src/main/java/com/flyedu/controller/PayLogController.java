package com.flyedu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.flyedu.common.Result;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.OrderService;
import com.flyedu.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
@Api(description = "支付日志管理")
@RestController
@RequestMapping("/orderService")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @ApiOperation(value = "生成微信支付二维码")
    @GetMapping("/creatNative/{orderNo}")
    public Result creatNative(@PathVariable String orderNo){
        System.out.println(orderNo);
        //获取微信支付二维码，及相关信息
        Map<String,Object> map = payLogService.creatNative(orderNo);
        System.out.println("生成微信支付二维码"+map);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "根据订单号查询订单状态")
    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println(map+"订单状态");
        if (map==null){
            throw new EduException(20001,"订单出错了！");
        }
        //如果不为空，获取map中的订单信息
        if (map.get("trade_state").equals("SUCCESS")){
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return Result.ok().message("支付成功");
        }
        return Result.ok().code(25000).message("支付中....");
    }

}

