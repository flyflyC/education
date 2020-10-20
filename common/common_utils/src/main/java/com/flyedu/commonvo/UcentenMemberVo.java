package com.flyedu.commonvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @ClassName UcenterMemberVo
 * @Author cai feifei
 * @date 2020.10.19 20:37
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
    @ApiModelProperty(value = "手机号")
    private String mobile;
}
