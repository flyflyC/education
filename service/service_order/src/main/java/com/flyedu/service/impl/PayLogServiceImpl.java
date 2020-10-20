package com.flyedu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.Order;
import com.flyedu.entity.PayLog;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.mapper.PayLogMapper;
import com.flyedu.service.OrderService;
import com.flyedu.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyedu.utils.HttpClient;
import com.flyedu.utils.WxPayInfoUtil;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;
    /**
     * 获取微信支付二维码接口
     * @param orderNo
     * @return
     */
    @Override
    public Map<String, Object> creatNative(String orderNo) {
        System.out.println(orderNo);
        try {
            //根据订单号获取订单信息
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            Order order = orderService.getOne(wrapper);
            System.out.println("订单信息"+order);
            //使用map设置生成二维码的参数
            Map<String,String> m = new HashMap<>();
            m.put("appid", WxPayInfoUtil.WX_PAY_APP_ID);
            m.put("mch_id", WxPayInfoUtil.WX_PAY_PARTNER);

            m.put("nonce_str", WXPayUtil.generateNonceStr());
            //课程标题
            m.put("body", order.getCourseTitle());
            //订单号
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");
            m.put("trade_type", "NATIVE");
            //发送httpclient请求，
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //设置xml格式的参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m, WxPayInfoUtil.WX_PAY_PARTNERKEY));
            client.setHttps(true);
            //执行post请求发送
            client.post();
            // 得到发送请求返回结果
            //返回内容，是使用xml格式返回
            String xml = client.getContent();

            //把xml格式转换map集合，把map集合返回
            Map<String,String> resultMap = WXPayUtil.xmlToMap(xml);

            //最终返回数据 的封装
            Map<String,Object> map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            //返回二维码操作状态码
            map.put("result_code", resultMap.get("result_code"));
            //二维码地址
            map.put("code_url", resultMap.get("code_url"));
            System.out.println("订单信息"+map);
            return map;
        }catch (Exception e){
            throw new EduException(20001,"生成二维码失败");
        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            //封装参数
            Map<String,String> m = new HashMap<>();
            m.put("appid", WxPayInfoUtil.WX_PAY_APP_ID);
            m.put("mch_id", WxPayInfoUtil.WX_PAY_PARTNER);
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //发送httpclient请求，
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            //得到内容
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6、转成Map再返回
            return resultMap;
        }catch (Exception e){
            throw new EduException(20001,"获取订单状态失败");
        }

    }

    @Override
    public void updateOrdersStatus(Map<String, String> map) {

        //从map中获取订单号
        String tradeNo = map.get("out_trade_no");
        //根据订单号查询订单信息
        QueryWrapper<Order> wrapper =new QueryWrapper<>();
        wrapper.eq("order_no",tradeNo);
        Order order = orderService.getOne(wrapper);

        if (order.getStatus() ==1){
            return;
        }else {
            order.setStatus(1);
            orderService.updateById(order);
        }

        //向支付表添加支付记录
        PayLog payLog = new PayLog();
        //订单号
        payLog.setOrderNo(tradeNo);
        //订单完成时间
        payLog.setPayTime(new Date());
        //支付类型 1微信
        payLog.setPayType(1);
        //总金额(分)
        payLog.setTotalFee(order.getTotalFee());

        //支付状态
        payLog.setTradeState(map.get("trade_state"));
        //流水号
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));

        System.out.println(payLog+"PayLog");
        baseMapper.insert(payLog);
    }
}
