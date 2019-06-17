package com.nevercome.icuriosity.common.result;

import com.nevercome.icuriosity.utils.RequestContextHolderUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 所有不合业务逻辑的错误都将由throw的形式抛出（BusinessException）
 * 而不是返回PlatformResult.failure (极少数情况会需要 比如密码错误？)
 * @author: sun
 * @date: 2019/6/17
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultErrorResult {

    public static final long serialVersionUID = 136400365941306385L;

    /**
     * Http响应状态码
     */
    private Integer status;

    /**
     * Http响应的英文提示
     */
    private String error;

    /**
     * 异常原因的精简信息
     */
    private String message;

    /**
     * 系统内部自定义的返回值编码，{@link ResultCode} 它是对错误更加详细的编码
     *
     * 备注：spring boot默认返回异常时，该字段为null
     */
    private Integer code;

    /**
     * 调用接口路径
     */
    private String path;

    /**
     * 异常的名字
     */
    private String exception;

    /**
     * 异常的错误传递的数据
     */
    private Object errors;

    /**
     * 时间戳
     */
    private Date timestamp;

    /**
     * 设置更详细的错误信息
     * @param errors
     * @return
     */
    public static DefaultErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus, Object errors) {
        DefaultErrorResult result = DefaultErrorResult.failure(resultCode, e, httpStatus);
        result.setErrors(errors);
        return result;
    }

    /**
     * 返回设置错误的结果
     * @param resultCode
     * @param e
     * @param httpStatus
     * @return
     */
    public static DefaultErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus) {
        DefaultErrorResult result = new DefaultErrorResult();
        result.setCode(resultCode.code());
        result.setMessage(resultCode.message());
        result.setStatus(httpStatus.value());
        result.setError(httpStatus.getReasonPhrase());
        result.setException(e.getClass().getName());
        result.setPath(RequestContextHolderUtil.getRequest().getRequestURI());
        result.setTimestamp(new Date());
        return result;
    }

    /**
     * 根据自定义业务异常类来返回错误结果
     * @param e
     * @return
     */
//    public static DefaultErrorResult failure(BusinessException e) {
//        ExceptionEnum ee = ExceptionEnum.getByEClass(e.getClass());
//        if (ee != null) {
//            return DefaultErrorResult.failure(ee.getResultCode(), e, ee.getHttpStatus(), e.getData());
//        }
//
//        DefaultErrorResult defaultErrorResult = DefaultErrorResult.failure(e.getResultCode() == null ?
//                ResultCode.SUCCESS : e.getResultCode(), e, HttpStatus.OK, e.getData());
//        if (!StringUtils.isEmpty(e.getMessage())) {
//            defaultErrorResult.setMessage(e.getMessage());
//        }
//        return defaultErrorResult;
//    }

}
