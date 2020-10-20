package com.flyedu.client;

import com.flyedu.common.Result;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName UcentenFileDegradeFeignClient
 * @Author cai feifei
 * @date 2020.10.20 20:13
 * @Version
 */
@Component
public class UcentenFileDegradeFeignClient implements UcentenClient{
    @Override
    public int countRegister(String day) {
        System.out.println("ucenten服务出现问题啦！！！");
        int t=0;
        return t;
    }
}
