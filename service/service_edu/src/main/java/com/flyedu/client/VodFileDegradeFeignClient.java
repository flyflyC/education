package com.flyedu.client;

import com.flyedu.common.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 熔断机制：在service-vod服务发生异常时执行
 * @ClassName VodFileDegradeFeignClient
 * @Author cai feifei
 * @date 2020.10.16 11:14
 * @Version
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public Result deleteAliyunVideo(String id) {
        return Result.error().message("vod服务出现问题啦！！！");
    }

    @Override
    public Result deleteAlyVideoBatch(List<String> videoList) {
        return Result.error().message("vod服务出现问题啦！！！");
    }
}
