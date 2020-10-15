package com.flyedu.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName VodUtil
 * @Author cai feifei
 * @date 2020.10.15 15:22
 * @Version
 */
@Component
public class VodUtil implements InitializingBean {
    @Value("${aliyun.vod.file.keyid}")
    private String keyId;
    @Value("${aliyun.vod.file.keysecret}")
    private String KeySecret;

    public static String KEY_ID;
    public static String KEY_SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID=keyId;
        KEY_SECRET=KeySecret;
    }
}
