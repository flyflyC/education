package com.flyedu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @ClassName OssService
 * @Author cai feifei
 * @date 2020.10.12 23:35
 * @Version
 */
public interface OssService {

    /**
     * 上传头像到oos
     * @param file
     * @return
     */
    public String uploadAvatar(MultipartFile file);
}
