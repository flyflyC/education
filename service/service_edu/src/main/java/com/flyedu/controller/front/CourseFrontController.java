package com.flyedu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyedu.client.OrderClient;
import com.flyedu.common.JwtUtils;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private EduChapterService chapterService;

    @Autowired
    private OrderClient orderClient;
    
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
    public Result getCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id查询课程详情
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询课程章节小节
        List<ChapterVo> chapterVideo = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id判断付费课程是否已经支付
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        /*if (StringUtils.isEmpty(memberId)){
            return Result.error().message("要登录才能购买，亲先登录吧！");
        }*/
        Boolean isBuy = orderClient.isBuy(courseId, memberId);
        System.out.println(isBuy);
        Map<String,Object> map = new HashMap<>();
        map.put("courseWebVo",courseWebVo);
        map.put("chapterVideo",chapterVideo);
        map.put("isBuy",isBuy);

        return Result.ok().data(map);
    }
}
