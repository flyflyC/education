package com.flyedu.service;

import com.flyedu.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flyedu.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-17
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 登录
     * @param user
     * @return
     */
    String login(UcenterMember user);

    /**
     * 注册
     * @param register
     * @return
     */
    void Register(RegisterVo register);

    /**
     * 根据openid查询是否存在该微信用户
     * @param openid
     * @return
     */
    UcenterMember getMemberByOpenid(String openid);

    /**
     * 查询某天注册的人数
     * @param day
     * @return
     */
    int countRegister(String day);
}
