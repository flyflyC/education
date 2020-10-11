package com.flyedu.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Description 统一结果返回集
 * @ClassName Result
 * @Author cai feifei
 * @date 2020.10.11 16:21
 * @Version
 */
@Data
public class Result {

    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("返回数据集")
    private Map<String,Object> data;

    /**
     * 构造方法私有
     */
    private Result() {

    }
    /**
     * 成功静态方法
     */
    public static Result ok(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    /**
     * 失败静态方法
     */
    public static Result error(){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
