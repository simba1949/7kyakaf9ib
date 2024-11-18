package buddy.common.request;

/**
 * @author anthony
 * @version 2024-11-18
 * @since 2023/11/28 20:36
 */
public class IdRequest extends AbstractRequest {
	private static final long serialVersionUID = 1L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}