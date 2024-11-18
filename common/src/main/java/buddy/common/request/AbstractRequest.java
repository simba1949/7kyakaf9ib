package buddy.common.request;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2024-11-18
 * @since 2024-11-18 13:19
 */
public abstract class AbstractRequest implements Serializable {
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
		return "AbstractRequest{" +
			"requestId='" + requestId + '\'' +
			'}';
	}
}