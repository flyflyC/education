package com.flyedu.entity.vo.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @ClassName UcentenMemberVo
 * @Author cai feifei
 * @date 2020.10.19 20:36
 * @Version
 */
@Data
public class UcentenMemberVo {
    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
