package com.flyedu.entity.vo.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 章节小节
 * @ClassName VideoVo
 * @Author cai feifei
 * @date 2020.10.14 13:56
 * @Version
 */
@Data
public class VideoVo {


    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "节点名称")
    private String title;

    /*@ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    private String videoOriginalName;*/
}
