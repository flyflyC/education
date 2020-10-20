package com.flyedu.mapper;

import com.flyedu.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
