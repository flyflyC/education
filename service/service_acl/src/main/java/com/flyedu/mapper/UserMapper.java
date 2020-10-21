package com.flyedu.mapper;

import com.flyedu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
