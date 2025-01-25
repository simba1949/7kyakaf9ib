package buddy.common.exception;

/**
 * <div>
 *    保证不合业务系统冲突，错误码使用前缀 CB
 *     CB-000000~CB-099999 系统成功错误码
 *     CB-100000~CB-200000 系统通用异常错误码
 *     CB-200000~CB-300000 数据库通用异常错误码
 *     CB-300000~CB-400000 和三方交互通用异常错误码
 * </div>
 *
 * @author anthony
 * @version 2024/09/11
 * @since 2023/10/19 13:56
 */
public enum ErrorCodeEnum {
	// 000000~099999 系统成功错误码
	SUCCESS("CB-000000", "成功"),

	// 100000~200000 系统通用异常错误码
	SYSTEM_ERROR("CB-100000", "系统异常"),
	PARAMETER_INVALID("CB-100001", "参数不合法"),
	PARAMETER_EXCEPTION("CB-100002", "参数异常"),
	PARAMETER_NOT_NULL("CB-100003", "参数不能为空"),

	// 200000~300000 数据库通用异常错误码
	DB_DATA_NOT_EXIST("CB-200000", "数据不存在"),
	DB_INSERT_FAIL("CB-200001", "数据新增失败"),
	DB_UPDATE_FAIL("CB-200002", "数据更新失败"),
	DB_DELETE_FAIL("CB-200003", "数据删除失败"),

	RPC_FAIL("CB-300000", "调用三方系统，失败"),
	RPC_TIMEOUT("CB-300001", "调用三方系统，超时"),
	RPC_NO_DATA("CB-300002", "调用三方系统，数据为空"),
	RPC_NO_PERMISSION("CB-300401", "没有权限调用三方系统"),
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