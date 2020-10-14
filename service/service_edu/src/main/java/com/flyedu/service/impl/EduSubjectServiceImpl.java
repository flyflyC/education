package com.flyedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.EduSubject;
import com.flyedu.entity.excel.SubjectEx;
import com.flyedu.entity.vo.subject.OneSubjectVo;
import com.flyedu.entity.vo.subject.TwoSubjectVo;
import com.flyedu.listener.SubjectExcelListener;
import com.flyedu.mapper.EduSubjectMapper;
import com.flyedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<OneSubjectVo> getAllOneTwoSubject() {
        //1.查询所有的一级课程
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjects = baseMapper.selectList(wrapperOne);
        //2.查询所有二级查询
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> twoSubjects = baseMapper.selectList(wrapperTwo);
        //创建list集合，封装数据返回到后端前端树形页面
        List<OneSubjectVo> subjectAll = new ArrayList<OneSubjectVo>();
        //3.封装一级课程
        for (int i=0;i<oneSubjects.size();i++){
            EduSubject oneSubject = oneSubjects.get(i);
            OneSubjectVo onesubjectVo = new OneSubjectVo();
            onesubjectVo.setId(oneSubject.getId());
            onesubjectVo.setTitle(oneSubject.getTitle());

            //4.封装二级课程
            List<TwoSubjectVo> twoSubjectAll = new ArrayList<>();
            for (int j=0;j<twoSubjects.size();j++){
                EduSubject twoSubject = twoSubjects.get(j);
                TwoSubjectVo twoSubjectVo = new TwoSubjectVo();
                //简化方法
                //BeanUtils.copyProperties(twoSubjects.get(j),twoSubjectVo);

                twoSubjectVo.setId(twoSubject.getId());
                twoSubjectVo.setTitle(twoSubject.getTitle());
                if (twoSubject.getParentId().equals(oneSubject.getId())){
                    twoSubjectAll.add(twoSubjectVo);
                }
            }
            onesubjectVo.setChildren(twoSubjectAll);
            subjectAll.add(onesubjectVo);
        }


        return subjectAll;
    }
}
