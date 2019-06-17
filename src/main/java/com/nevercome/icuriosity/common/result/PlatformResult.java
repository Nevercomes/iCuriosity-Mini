package com.nevercome.icuriosity.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 平台通用返回结果
 * @author: sun
 * @date: 2019/6/17
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatformResult implements Result{

    public static final long serialVersionUID = 874200365941306385L;

    private Integer code;

    private String message;

    private Object data;

    /**
     * 返回平凡的成功信息
     * @return
     */
    public static PlatformResult success() {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 返回携带数据的成功信息
     * @param data
     * @return
     */
    public static PlatformResult success(Object data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 返回设置错误状态及消息的信息
     * @param resultCode
     * @return
     */
    public static PlatformResult failure(ResultCode resultCode) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回设置错误状态及消息和更详细的错误信息
     * @param resultCode
     * @param data
     * @return
     */
    public static PlatformResult failure(ResultCode resultCode, Object data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     * 设置错误的返回消息 默认码值为参数异常
     * @param message
     * @return
     */
    public static PlatformResult failure(String message) {
        PlatformResult result = new PlatformResult();
        result.setCode(ResultCode.PARAM_IS_INVALID.code());
        result.setMessage(message);
        return result;
    }

    /**
     * 使用自定义的枚举类设置code和msg
     * @param resultCode
     */
    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

}
