package com.nevercome.icuriosity.common.result;

import java.lang.annotation.*;

/**
 * API返回值注解
 * 接口返回结果增强
 * 会通过拦截器拦截后放入标记，在ResponseResultHandler 进行结果处理
 * @author: sun
 * @date: 2019/6/17
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

    Class <? extends Result> value() default PlatformResult.class;

}
