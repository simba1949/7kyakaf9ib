package buddy.common.response;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2024-11-18
 * @since 2024-11-18 14:10
 */
public class AbstractResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String requestId; // 请求 id

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "AbstractResponse{" +
			"requestId='" + requestId + '\'' +
			'}';
	}
}