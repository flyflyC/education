package com.flyedu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @ClassName orderClient
 * @Author cai feifei
 * @date 2020.10.20 17:36
 * @Version
 */
@FeignClient(name = "service-order",fallback = OrderFileDegradeFeignClient.class)
@Component
public interface OrderClient {

    @GetMapping("/orderService/isBuy/{courseId}/{memberId}")
    public Boolean isBuy(@PathVariable String courseId, @PathVariable String memberId);
}
