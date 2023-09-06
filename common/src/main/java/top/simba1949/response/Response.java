package top.simba1949.response;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean status;
    private String code;
    private String message;
    private T data;

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
    public static Response<?> success() {
        return success(ErrorEnum.SUCCESS.getCode(), null, null);
    }

    /**
     * 响应成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data) {
        return success(ErrorEnum.SUCCESS.getCode(), null, data);
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
        return fail(ErrorEnum.SYSTEM_ERROR.getCode(), message, null);
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