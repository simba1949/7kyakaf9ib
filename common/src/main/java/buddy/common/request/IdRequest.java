package buddy.common.request;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2024/09/11
 * @since 2023/11/28 20:36
 */
public class IdRequest implements Serializable {
	private static final long serialVersionUID = 1781002564312254129L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}