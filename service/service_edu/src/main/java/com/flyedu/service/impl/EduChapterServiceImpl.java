package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.EduChapter;
import com.flyedu.entity.EduSubject;
import com.flyedu.entity.EduVideo;
import com.flyedu.entity.vo.chapter.ChapterVo;
import com.flyedu.entity.vo.chapter.VideoVo;
import com.flyedu.entity.vo.subject.OneSubjectVo;
import com.flyedu.entity.vo.subject.TwoSubjectVo;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.mapper.EduChapterMapper;
import com.flyedu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService videoService;
    /**
     * 通过课程ID查询所有章节
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.通过课程id查询所有的章节
        QueryWrapper<EduChapter> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("course_id",courseId);
        List<EduChapter> oneChapters = baseMapper.selectList(wrapperOne);
        //2.查询所有小节查询
        QueryWrapper<EduVideo> wrapperTwo = new QueryWrapper<>();
        wrapperOne.eq("course_id",courseId);
        List<EduVideo> videos = videoService.list(wrapperTwo);
        //创建list集合，封装数据返回到后端前端树形页面
        List<ChapterVo> chapterVoAll = new ArrayList<ChapterVo>();
        //3.封装章节
        for (int i=0;i<oneChapters.size();i++){
            //得到每个章节
            EduChapter oneChapter = oneChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(oneChapter,chapterVo);

            //4.遍历小节
            List<VideoVo> videoVoAll = new ArrayList<>();
            for (int j=0;j<videos.size();j++){
                EduVideo video = videos.get(j);
                VideoVo videoVo = new VideoVo();


                if (video.getChapterId().equals(oneChapter.getId())){
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoAll.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoAll);
            chapterVoAll.add(chapterVo);
        }
        return chapterVoAll;
    }

    /**
     * 删除章节：先查询章节是否含有小节，如果含有，不能直接删除，要先删除小节，才能删除章节
     * @param id
     * @return
     */
    @Override
    public boolean deleteChapter(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        int count = videoService.count(wrapper);
        if (count>0){
            throw new EduException(20001,"不能删除");
        }else {
            int i = baseMapper.deleteById(id);
            return i>0;
        }
    }

    @Override
    public boolean removeChapterById(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        int delete = baseMapper.delete(wrapper);
        return delete>0;
    }
}
