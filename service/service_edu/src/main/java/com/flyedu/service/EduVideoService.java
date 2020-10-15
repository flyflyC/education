package com.flyedu.service;

import com.flyedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除小节
     * @param courseId
     * @return
     */
    Boolean removeVideoById(String courseId);
}
