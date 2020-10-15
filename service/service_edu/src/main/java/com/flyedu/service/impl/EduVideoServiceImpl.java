package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.EduVideo;
import com.flyedu.mapper.EduVideoMapper;
import com.flyedu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    /**
     * 根据课程id删除小节
     * @param courseId
     * @return
     */
    @Override
    public Boolean removeVideoById(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        int delete = baseMapper.delete(wrapper);

        return delete>0;
    }
}
