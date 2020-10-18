package com.flyedu.controller;


import com.flyedu.common.JwtUtils;
import com.flyedu.common.Result;
import com.flyedu.entity.UcenterMember;
import com.flyedu.entity.vo.RegisterVo;
import com.flyedu.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-17
 */
@Api(description = "用户中心")
@RestController
@RequestMapping("/ucenterService")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result loginUser(@RequestBody UcenterMember user){

        //调用service方法实现登录，并返回token
        String token = ucenterMemberService.login(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public Result RegisterUser(@RequestBody RegisterVo register){
        ucenterMemberService.Register(register);
        return Result.ok();
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(memberId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userInfo",member);
        return Result.ok().data(map);
    }
}

