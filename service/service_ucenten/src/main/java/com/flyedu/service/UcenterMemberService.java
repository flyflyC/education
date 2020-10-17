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
    String Register(RegisterVo register);
}
