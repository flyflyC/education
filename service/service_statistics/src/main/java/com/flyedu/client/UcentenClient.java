package com.flyedu.client;

import com.flyedu.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description
 * @ClassName UcentenClient
 * @Author cai feifei
 * @date 2020.10.20 20:12
 * @Version
 */
@FeignClient(name = "service-ucenten",fallback = UcentenFileDegradeFeignClient.class)
//@FeignClient(name = "service-ucenten")
@Component
public interface UcentenClient {
    /**
     * 调用接口获取注册人数
     * @param day
     * @return
     */
    @GetMapping("/ucenterService/countRegister/{day}")
    public int countRegister(@PathVariable String day);
}
