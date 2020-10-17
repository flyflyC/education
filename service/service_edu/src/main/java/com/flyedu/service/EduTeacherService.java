package com.flyedu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
