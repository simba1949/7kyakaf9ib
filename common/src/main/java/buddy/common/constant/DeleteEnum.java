package buddy.common.constant;

/**
 * 删除状态枚举
 *
 * @author anthony
 * @version 2024-09-11
 * @since 2024-09-11 11:28
 */
public enum DeleteEnum {
	YES(1, "已删除"),
	NO(0, "未删除");

	private final int code;
	private final String desc;

	DeleteEnum(int code, String desc) {
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