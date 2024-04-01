package vip.openpark.armguard.common.constant;

/**
 * @author anthony
 * @version 2023/11/30 15:50
 */
public enum SortEnum {
	ASC("asc"),
	DESC("desc"),
	;
	private final String code;

	SortEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}