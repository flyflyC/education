package com.flyedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.flyedu.entity.EduSubject;
import com.flyedu.entity.excel.SubjectEx;
import com.flyedu.listener.SubjectExcelListener;
import com.flyedu.mapper.EduSubjectMapper;
import com.flyedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-13
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        //文件输入流
        try {
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectEx.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
