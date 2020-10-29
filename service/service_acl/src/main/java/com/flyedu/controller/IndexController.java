package com.flyedu.controller;

import com.alibaba.fastjson.JSONObject;
import com.flyedu.common.Result;
import com.flyedu.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName IndexController
 * @Author cai feifei
 * @date 2020.10.29 09:56
 * @Version
 */
@RestController
@RequestMapping("/admin/acl/index")
@CrossOrigin
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public Result info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.ok().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public Result getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        HashMap<String, Object> map = new HashMap<>();
        map.put("permissionList", permissionList);
        return Result.ok().data(map);
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }

}
