package com.flyedu.service.impl;

import com.flyedu.entity.User;
import com.flyedu.mapper.UserMapper;
import com.flyedu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
