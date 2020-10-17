package com.flyedu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @ClassName VodService
 * @Author cai feifei
 * @date 2020.10.15 15:11
 * @Version
 */
public interface VodService {
    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file) throws IOException;

    /**
     * 同时删除阿里云上的多个视频
     * @param videoList
     * @return
     */
    void deleteAlyVideoBach(List videoList);
}
