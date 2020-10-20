package com.flyedu.service;

import com.flyedu.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
public interface PayLogService extends IService<PayLog> {

    /**
     * 获取微信支付二维码，及相关信息
     * @param orderNo
     * @return
     */
    Map<String, Object> creatNative(String orderNo);

    /**
     * 根据订单号查询订单状态
     * @param orderNo
     * @return
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     * 添加记录到支付表，更新订单表订单状态
     * @param map
     */
    void updateOrdersStatus(Map<String, String> map);
}
