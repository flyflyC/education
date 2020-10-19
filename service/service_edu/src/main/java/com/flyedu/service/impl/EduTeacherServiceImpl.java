package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.entity.EduTeacher;
import com.flyedu.entity.vo.TeacherVo;
import com.flyedu.mapper.EduTeacherMapper;
import com.flyedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> page) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        Page<EduTeacher> eduTeacherPage = baseMapper.selectPage(page, wrapper);

        List<EduTeacher> records = eduTeacherPage.getRecords();

        long current = eduTeacherPage.getCurrent();
        long pages = eduTeacherPage.getPages();
        long size = eduTeacherPage.getSize();
        long total = eduTeacherPage.getTotal();
        //下一页
        boolean hasNext = eduTeacherPage.hasNext();
        //上一页
        boolean hasPrevious = eduTeacherPage.hasPrevious();

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public Map<String, Object> getTeacherInfoList(Page<EduTeacher> page, TeacherVo teacherVo) {
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询,动态sql
        String name = teacherVo.getName();
        Integer level = teacherVo.getLevel();
        String begin = teacherVo.getBegin();
        String end = teacherVo.getEnd();
        System.out.println(teacherVo.toString());
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq(true,"level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)) {

            wrapper.le("gmt_modified",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现分页
        baseMapper.selectPage(page,wrapper);
        //获取总条数
        Long total = page.getTotal();
        //返回对象集合
        List<EduTeacher> teachers = page.getRecords();
        //封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("teachers", teachers);
        return map;
    }
}
