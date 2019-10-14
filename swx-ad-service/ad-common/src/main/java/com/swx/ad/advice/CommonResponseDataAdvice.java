package com.swx.ad.advice;

import com.swx.ad.annotation.IgnoreResponseAdvice;
import com.swx.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一 REST 接口返回
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     *  当有注解  @IgnoreResponseAdvice  时，不对返回对象进行包装
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        CommonResponse commonResponse = new CommonResponse(0,"");
        if(null != o && o instanceof CommonResponse){
            commonResponse = (CommonResponse) o;
        }else {
            commonResponse.setData(o);
        }
        return commonResponse;
    }
}
