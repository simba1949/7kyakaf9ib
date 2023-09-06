package top.simba1949.response;

/**
 * @author anthony
 * @version 2023/9/6
 */
public enum ErrorEnum {
    SUCCESS("0", "成功"),
    SYSTEM_ERROR("999999", "系统异常"),
    ;

    private final String code;
    private final String desc;

    ErrorEnum(String code, String desc) {
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
        return "ErrorEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                "} " + super.toString();
    }
}
