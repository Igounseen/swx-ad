package com.swx.ad.advice;

import com.swx.ad.exception.AdException;
import com.swx.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(AdException.class)
    public CommonResponse<String> handlerException(HttpServletRequest request, AdException ex){
        CommonResponse<String> commonResponse = new CommonResponse<>(-1,"Error!");
        commonResponse.setData(ex.getMessage());
        return commonResponse;
    }

}
