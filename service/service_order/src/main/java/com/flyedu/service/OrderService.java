package com.flyedu.service;

import com.flyedu.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据课程id和用户id创建订单
     * @param courseId
     * @param memberId
     * @return
     */
    String creatOrder(String courseId, String memberId);
}
