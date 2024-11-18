package buddy.common.request;

/**
 * @author anthony
 * @version 2024-11-18
 * @since 2023/09/06 17:16
 */
public class KeywordRequest extends AbstractRequest {
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
			"} " + super.toString();
	}
}