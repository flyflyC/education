package com.flyedu.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class UcenterMemberVo {

    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
