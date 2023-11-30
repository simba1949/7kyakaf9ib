package vip.openpark.armguard.common.exception;

/**
 * <div>
 *     000000~099999 系统成功错误码
 *     100000~200000 系统通用异常错误码
 *     200000~300000 数据库通用异常错误码
 * </div>
 *
 * @author anthony
 * @version 2023/9/6
 */
public enum ErrorCodeEnum {
	// 000000~099999 系统成功错误码
	SUCCESS("000000", "成功"),

	// 100000~200000 系统通用异常错误码
	SYSTEM_ERROR("100000", "系统异常"),
	PARAMETER_INVALID("100001", "参数不合法"),
	PARAMETER_EXCEPTION("100002", "参数异常"),
	PARAMETER_NOT_NULL("100003", "参数不能为空"),

	// 200000~300000 数据库通用异常错误码
	DB_INSERT_FAIL("200000", "数据新增失败"),
	DB_UPDATE_FAIL("200001", "数据更新失败"),
	DB_DELETE_FAIL("200002", "数据删除失败"),
	;

	private final String code;
	private final String desc;

	ErrorCodeEnum(String code, String desc) {
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