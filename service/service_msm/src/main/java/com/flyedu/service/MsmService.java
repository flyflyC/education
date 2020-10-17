package com.flyedu.service;

import java.util.HashMap;

/**
 * @Description
 * @ClassName MsmService
 * @Author cai feifei
 * @date 2020.10.17 16:42
 * @Version
 */
public interface MsmService {
    /**
     * 发送短信
     * @param map
     * @param phone
     * @return
     */
    boolean send(HashMap<String, Object> map, String phone);
}
