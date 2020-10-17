package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.EduTeacher;
import com.flyedu.mapper.EduTeacherMapper;
import com.flyedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-11
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    @Cacheable(key = "'teacherList'",value = "teachers")
    public List<EduTeacher> getTeacherList(QueryWrapper<EduTeacher> teacherQueryWrapper) {
        List<EduTeacher> teachers = baseMapper.selectList(teacherQueryWrapper);
        return teachers;
    }
}
