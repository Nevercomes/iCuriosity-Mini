package com.nevercome.icuriosity.common.result;

import com.nevercome.icuriosity.utils.JsonMapper;
import com.nevercome.icuriosity.utils.RequestContextHolderUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/**
 * 接口响应体处理器
 * 正确的结果返回的PlatformResult 不需要再包装
 *
 * @author: sun
 * @date: 2019/6/17
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextHolderUtil.getRequest()
                .getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);
        return responseResultAnn != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextHolderUtil.getRequest().getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);
        Class<? extends Result> resultClazz = responseResultAnn.value();

//        System.err.println("ResponseBodyAdvice： " + body);

        if (resultClazz.isAssignableFrom(PlatformResult.class)) {
            if (body instanceof DefaultErrorResult) {
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult) body;
                return transform(defaultErrorResult);
            } else if (body instanceof String) {
                return JsonMapper.toJsonString(PlatformResult.success(body));
            } else if (body instanceof Map) {
                Map error = (Map) body;
                return PlatformResult.builder()
                        .code(-1)
                        .message((String) error.get("message"))
                        .data(error)
                        .build();
            }
            return body;
        }
        return PlatformResult.success(body);
    }

    private PlatformResult transform(DefaultErrorResult defaultErrorResult) {
        return PlatformResult.builder()
                .code(defaultErrorResult.getCode())
                .message(defaultErrorResult.getMessage())
                .data(defaultErrorResult.getErrors())
                .build();
    }
}
