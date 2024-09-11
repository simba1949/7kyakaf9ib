package buddy.common.exception;

/**
 * 业务异常类
 *
 * @author anthony
 * @version 2024/09/11
 * @since 2023/10/19 13:56
 */
public class BizException extends RuntimeException {
	private static final long serialVersionUID = 3242183897790192290L;

	private String code;
	private String message;

	public BizException(ErrorCodeEnum errorCodeEnum) {
		this.code = errorCodeEnum.getCode();
		this.message = errorCodeEnum.getDesc();
	}

	public BizException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public BizException(Throwable cause, String code, String message) {
		super(cause);
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}