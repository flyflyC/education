package com.flyedu.service;

import com.flyedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flyedu.entity.vo.subject.OneSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-13
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 通过Excel读取内容添加到
     * @param file
     * @param subjectService
     */
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    /**
     * 课程分类列表（树形）
     * @return
     */
    List<OneSubjectVo> getAllOneTwoSubject();
}
