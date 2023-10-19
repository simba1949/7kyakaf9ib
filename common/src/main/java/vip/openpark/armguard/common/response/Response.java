package vip.openpark.armguard.common.response;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SUCCESS_CODE = ResponseCodeEnum.SUCCESS.getCode(); // 响应成功码
    public static final String SUCCESS_MESSAGE = ResponseCodeEnum.SUCCESS.getDesc(); // 响应成功信息
    public static final String FAIL_CODE = ResponseCodeEnum.SYSTEM_ERROR.getCode(); // 响应异常码
    public static final String FAIL_MESSAGE = ResponseCodeEnum.SYSTEM_ERROR.getDesc(); // 响应异常信息

    /**
     * 响应状态：true 表示请求成功，false 表示请求失败
     */
    private Boolean status;
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息，例如错误提示
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    private ResponseCodeEnum responseCodeEnum;

    private Response(Boolean status, String code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 响应成功
     *
     * @return
     */
    public static <T> Response<T> success() {
        return success(null);
    }

    /**
     * 响应成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data) {
        return success(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    /**
     * 响应成功
     *
     * @param responseCodeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(ResponseCodeEnum responseCodeEnum, T data) {
        return success(responseCodeEnum.getCode(), responseCodeEnum.getDesc(), data);
    }

    /**
     * 响应成功
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(String code, String message, T data) {
        return new Response<>(Boolean.TRUE, code, message, data);
    }


    /**
     * 响应失败
     *
     * @param message
     * @return
     */
    public static Response<?> fail(String message) {
        return fail(FAIL_CODE, message);
    }

    /**
     * 响应失败
     *
     * @param responseCodeEnum
     * @return
     */
    public static Response<?> fail(ResponseCodeEnum responseCodeEnum) {
        return fail(responseCodeEnum.getCode(), responseCodeEnum.getDesc());
    }

    /**
     * 响应失败
     *
     * @param code
     * @param message
     * @return
     */
    public static Response<?> fail(String code, String message) {
        return fail(code, message, null);
    }

    /**
     * 响应失败
     *
     * @param responseCodeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(ResponseCodeEnum responseCodeEnum, T data) {
        return fail(responseCodeEnum.getCode(), responseCodeEnum.getDesc(), data);
    }

    /**
     * 响应失败
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(String code, String message, T data) {
        return new Response<>(Boolean.FALSE, code, message, data);
    }

    // =============================================
    // =========== getter/setter/toString ==========
    // =============================================

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}