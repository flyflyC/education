package com.flyedu.entity.vo.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 一级分类
 * @ClassName OneSubjectVo
 * @Author cai feifei
 * @date 2020.10.13 22:37
 * @Version
 */
@Data
public class OneSubjectVo {
    private String id;
    private String title;

    /**
     * 一个一级分类有多个二级分类
     */
    private List<TwoSubjectVo> children = new ArrayList<>();
}
