package buddy.common.constant;

/**
 * 起禁用状态枚举
 *
 * @author anthony
 * @version 2024-09-11
 * @since 2024-09-11 11:30
 */
public enum EnableEnum {
	DISABLE(0, "禁用"),
	ENABLE(1, "启用");
	private final int code;
	private final String desc;

	EnableEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}