package buddy.common.request;

/**
 * @author anthony
 * @version 2024-11-18
 * @since 2023/10/19 13:56
 */
public class PageRequest<T> extends AbstractRequest {
	private static final long serialVersionUID = 1L;

	private Integer pageNum = 1; // 页码，默认第一页
	private Integer pageSize = 20; // 每页数量，默认20

	private T condition; // 查询条件

	private String sortField; // 排序字段
	private String sort; // 排序规则 DESC/ASC

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

	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	@Override
	public String toString() {
		return "PageRequest{" +
			"pageNum=" + pageNum +
			", pageSize=" + pageSize +
			", condition=" + condition +
			", sort='" + sort + '\'' +
			", sortField='" + sortField + '\'' +
			"} " + super.toString();
	}
}