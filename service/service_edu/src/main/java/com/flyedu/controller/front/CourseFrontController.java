package com.flyedu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.common.Result;
import com.flyedu.entity.EduCourse;
import com.flyedu.entity.vo.frontvo.CourseFrontVo;
import com.flyedu.entity.vo.frontvo.CourseWebVo;
import com.flyedu.entity.vo.chapter.ChapterVo;
import com.flyedu.service.EduChapterService;
import com.flyedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName CourseFrontController
 * @Author cai feifei
 * @date 2020.10.19 10:04
 * @Version
 */
@Api(description = "前台课程展示")
@RestController
@RequestMapping("/eduService/courseFront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    EduChapterService chapterService;
    
    @ApiOperation(value = "多条件查询")
    @PostMapping("/pageCourseCondition/{current}/{limit}")
    public Result pageCourseCondition(@PathVariable("current")Integer current,
                                       @PathVariable("limit") Integer limit,
                                       @RequestBody(required = false) CourseFrontVo courseFrontVo){
        //创建page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "获取课程详情")
    @GetMapping("/getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId){
        //根据课程id查询课程详情
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //System.out.println(courseWebVo.toString());
        //根据课程id查询课程章节小节
        List<ChapterVo> chapterVideo = chapterService.getChapterVideoByCourseId(courseId);

        Map<String,Object> map = new HashMap<>();
        map.put("courseWebVo",courseWebVo);
        map.put("chapterVideo",chapterVideo);

        return Result.ok().data(map);
    }
}
