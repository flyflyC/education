package com.flyedu.exceptionhandler;

import com.flyedu.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @ClassName GlobalExceptionHandler
 * @Author cai feifei
 * @date 2020.10.11 18:53
 * @Version
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @ExceptionHandler 指定出现什么异常执行这个方法
     *  @ResponseBody 为了返回数据
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理..");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.error().message("执行了ArithmeticException异常处理..");
    }


    /**
     * 自定义异常
     * 为了返回数据
     * @param e
     * @return
     */
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public Result error(EduException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
