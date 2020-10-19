package com.flyedu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flyedu.entity.vo.TeacherVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-11
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 根据id降序查询前4条讲师数据
     * @param teacherQueryWrapper
     * @return
     */
    List<EduTeacher> getTeacherList(QueryWrapper<EduTeacher> teacherQueryWrapper);

    /**
     * 前台讲师分页展示
     * @param page
     * @return
     */
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> page);

    /**
     * 后台讲师分页多条件查询
     * @param page
     * @param teacherVo
     * @return
     */
    Map<String, Object> getTeacherInfoList(Page<EduTeacher> page, TeacherVo teacherVo);
}
