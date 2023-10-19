package vip.openpark.armguard.common.response;

/**
 * 000000~099999 系统成功响应码
 * 100000~200000 系统通用异常响应码
 *
 * @author anthony
 * @version 2023/9/6
 */
public enum ResponseCodeEnum {
    SUCCESS("000000", "成功"),

    SYSTEM_ERROR("100000", "系统异常"),
    PARAMETER_INVALID("100001", "参数不合法"),
    PARAMETER_EXCEPTION("100002", "参数异常"),
    PARAMETER_NOT_NULL("100003", "参数不能为空"),
    ;

    private final String code;
    private final String desc;

    ResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "ResponseCodeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}