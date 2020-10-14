package com.flyedu.entity.vo.chapter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 章节
 * @ClassName ChapterVo
 * @Author cai feifei
 * @date 2020.10.14 13:55
 * @Version
 */
@Data
public class ChapterVo {
    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示小节")
    private List<VideoVo> children = new ArrayList<>();
}
