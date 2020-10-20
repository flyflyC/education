package com.flyedu.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName WxPayUtil
 * @Author cai feifei
 * @date 2020.10.20 14:08
 * @Version
 */
@Component
public class WxPayInfoUtil implements InitializingBean {

    /**
     * 关联的公众号appid
     */
    @Value("${weixin.pay.appid}")
    private String appId;
    /**
     * 商户号
     */
    @Value("${weixin.pay.partner}")
    private String partner;

    /**
     * 回调地址
     */
    @Value("${weixin.pay.partnerkey}")
    private String partnerKey;

    public static String WX_PAY_APP_ID;
    public static String WX_PAY_PARTNER;
    public static String WX_PAY_PARTNERKEY;


    @Override
    public void afterPropertiesSet() throws Exception {
        WX_PAY_APP_ID=appId;
        WX_PAY_PARTNER=partner;
        WX_PAY_PARTNERKEY=partnerKey;
    }
}
