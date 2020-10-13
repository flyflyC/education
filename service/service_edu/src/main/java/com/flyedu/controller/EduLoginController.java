package com.flyedu.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.flyedu.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Description 测试后台前端
 * @ClassName EduLogin
 * @Author cai feifei
 * @date 2020.10.12 11:09
 * @Version
 */
@RestController
@RequestMapping("/teacher/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public Result login(String username,String password){
        HashMap<String, Object> map = new HashMap<>();
        String admin="admin";
        map.put("token",1);
        //map.put("username",username);
        //map.put("password",password);
        return Result.ok().data(map);
    }
    @GetMapping("/info")
    public Result getInfo(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok().data(map);
    }
}
