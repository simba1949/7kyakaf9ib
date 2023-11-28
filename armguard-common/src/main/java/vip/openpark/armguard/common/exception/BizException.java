package vip.openpark.armguard.common.exception;

import vip.openpark.armguard.common.response.ResponseCodeEnum;

/**
 * @author anthony
 * @version 2023/10/19 11:14
 */
public class BizException extends RuntimeException {
    private String code;
    private String message;

    public BizException(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getDesc();
    }

    public BizException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(Throwable cause, String code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}