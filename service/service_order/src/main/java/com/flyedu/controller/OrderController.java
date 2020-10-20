package com.flyedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.common.JwtUtils;
import com.flyedu.common.Result;
import com.flyedu.entity.Order;
import com.flyedu.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
@Api(description = "订单管理")
@RestController
@RequestMapping("/orderService")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "创建订单")
    @PostMapping("/creatOrder/{courseId}")
    public Result saveOrder(@PathVariable String courseId, HttpServletRequest request){
        //从token中获取用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //创建订单
        String orderNo = orderService.creatOrder(courseId,memberId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderNo",orderNo);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "根据订单号获取订单信息")
    @PostMapping("/getOrderInfo/{orderNo}")
    public Result getOrderInfo(@PathVariable String orderNo){

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("order",order);
        return Result.ok().data(map);
    }

    @GetMapping("/isBuy/{courseId}/{memberId}")
    public Boolean isBuy(@PathVariable String courseId,@PathVariable String memberId){

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        if (count>0){
            return true;
        }else {
            return false;
        }
    }
}

