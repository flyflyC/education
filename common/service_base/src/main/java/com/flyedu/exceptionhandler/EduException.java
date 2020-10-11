package com.flyedu.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @ClassName EduException
 * @Author cai feifei
 * @date 2020.10.11 19:07
 * @Version
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EduException extends RuntimeException{
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;
}
