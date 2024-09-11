package buddy.common.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author anthony
 * @version 2024/09/11
 * @since 2023/09/06 17:16
 */
public class PageResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	private Integer pageNum;
	/**
	 * 每页数量
	 */
	private Integer pageSize;

	/**
	 * 总页数
	 */
	private Long pages;
	/**
	 * 总条数
	 */
	private Long total;

	/**
	 * 数据
	 */
	private List<T> data;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPages() {
		return pages;
	}

	public void setPages(Long pages) {
		this.pages = pages;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PageResult{" +
			"pageNum=" + pageNum +
			", pageSize=" + pageSize +
			", pages=" + pages +
			", total=" + total +
			", data=" + data +
			'}';
	}
}