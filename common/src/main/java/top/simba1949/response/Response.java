package top.simba1949.response;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class Response<T> implements Serializable {
    public static final String CODE_SUCCESS = "SYS000"; // 响应码：响应成功
    public static final String CODE_FAIL = "SYS999"; // 响应码：系统异常

    private static final long serialVersionUID = 1L;

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
        return success(CODE_SUCCESS, null, null);
    }

    /**
     * 响应成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data) {
        return success(CODE_SUCCESS, null, data);
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
        return fail(CODE_FAIL, message, null);
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