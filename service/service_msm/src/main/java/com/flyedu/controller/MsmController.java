package com.flyedu.controller;

import com.flyedu.common.Result;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.MsmService;
import com.flyedu.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName MsmController
 * @Author cai feifei
 * @date 2020.10.17 16:40
 * @Version
 */
@Api(description = "短信服务")
@RestController
@RequestMapping("/eduMsm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "发送短信验证码")
    @GetMapping("/send/{phone}")
    public Result sendMsm(@PathVariable String phone){
        //从redis中获取验证码
        Object code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return Result.ok();
        }
        //生成6位数验证码
        code = RandomUtil.getSixBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);
        boolean isCode = msmService.send(map,phone);
        System.out.println(isCode);
        if (isCode){
            //短信发送成功后把验证码放到redis中，并设置5分钟的有效时长
            redisTemplate.opsForValue().set(phone, (String) code,5, TimeUnit.MINUTES);
            return Result.ok();
        }else {
            throw new EduException(20001,"发送短信失败！请联系后台人员");
        }

    }
}
