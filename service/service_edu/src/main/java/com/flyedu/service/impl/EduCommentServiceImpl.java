package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.entity.EduComment;
import com.flyedu.entity.EduCourse;
import com.flyedu.mapper.EduCommentMapper;
import com.flyedu.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-19
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public Map<String, Object> getPageComment(Page<EduComment> page, String courseId) {
        //构建条件
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.selectPage(page,wrapper);

        //获取总条数
        Long total = page.getTotal();
        List<EduComment> records = page.getRecords();
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("comments", records);
        map.put("current", page.getCurrent());
        map.put("pages", page.getPages());
        map.put("size", page.getSize());
        map.put("hasNext", page.hasNext());
        map.put("hasPrevious", page.hasPrevious());
        return map;
    }
}
