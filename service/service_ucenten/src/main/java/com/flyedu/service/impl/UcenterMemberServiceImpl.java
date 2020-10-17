package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.common.JwtUtils;
import com.flyedu.common.MD5;
import com.flyedu.entity.UcenterMember;
import com.flyedu.entity.vo.RegisterVo;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.mapper.UcenterMemberMapper;
import com.flyedu.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-17
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;
    @Override
    public String login(UcenterMember user) {

        //获取登录手机号和密码
        String mobile = user.getMobile();
        String password = user.getPassword();


        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw  new EduException(20001,"手机号或者密码为空！");
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);

        //判断是否存在该用户
        if (ucenterMember==null){
            throw  new EduException(20001,"用户不存在！");
        }
        //判断密码是否正确
        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw  new EduException(20001,"密码错误！");
        }
        //判断是否禁用
        if (ucenterMember.getIsDeleted()){
            throw  new EduException(20001,"用户已被禁用！");
        }

        //登录成功，使用JWT生成token
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        System.out.println(token);
        return token;
    }

    @Override
    public String Register(RegisterVo register) {
        String nickname = register.getNickname();
        String mobile = register.getMobile();

        String password = register.getPassword();

        String code = register.getCode();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)){
            throw  new EduException(20001,"手机号、密码和验证码不能为空！");
        }

        //获取验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)){
            throw  new EduException(20001,"验证码错误！");
        }
        return null;
    }
}
