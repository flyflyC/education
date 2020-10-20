package com.flyedu.client;

import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName OrderFileDegradeFeignClient
 * @Author cai feifei
 * @date 2020.10.20 17:36
 * @Version
 */
@Component
public class OrderFileDegradeFeignClient implements OrderClient{
    @Override
    public Boolean isBuy(String courseId, String memberId) {
        System.out.println("order服务出问题了");
        return null;
    }
}
