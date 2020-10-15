package com.flyedu.service;

import com.flyedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flyedu.entity.vo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-14
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 通过课程ID查询所有章节
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);


    /**
     * 删除章节：先查询章节是否含有小节，如果含有，不能直接删除，要先删除小节，才能删除章节
     * @param id
     * @return
     */
    boolean deleteChapter(String id);

    /**
     * 根据课程id删除章节
     * @param courseId
     * @return
     */
    boolean removeChapterById(String courseId);
}
