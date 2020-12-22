package com.tensquare.article.controller;

import com.tensquare.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/22 10:03
 * 4
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handler(Exception e){
      log.info("处理异常");
      return Result.fail(e.getMessage());
    }

}
