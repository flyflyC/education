package com.flyedu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 课程提交视图
 * @ClassName CoursePublishVo
 * @Author cai feifei
 * @date 2020.10.15 08:13
 * @Version
 */
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    /**
     *  只用于显示
     */
    private String price;
}
