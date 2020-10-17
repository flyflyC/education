package com.flyedu.client;

import com.flyedu.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description
 * @ClassName VodClient
 * @Author cai feifei
 * @date 2020.10.15 21:55
 * @Version
 */
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    /**
     * 定义调用方法路径
     * 根据视频id删除阿里云视频
     * @param id
     * @return
     */
    @DeleteMapping("/eduVod/video/removeAliyunVideo/{id}")
    public Result deleteAliyunVideo(@PathVariable("id") String id);

    /**
     * 删除阿里云上的多个视频
     * @param videoList
     * @return
     */
    @DeleteMapping("/eduVod/video/removeAlyVideoBatch")
    public Result deleteAlyVideoBatch(@RequestParam("videoList") List<String> videoList);
}
