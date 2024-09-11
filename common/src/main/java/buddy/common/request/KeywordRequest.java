package buddy.common.request;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2024/09/11
 * @since 2023/09/06 17:16
 */
public class KeywordRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "KeywordRequest{" +
			"keyword='" + keyword + '\'' +
			'}';
	}
}