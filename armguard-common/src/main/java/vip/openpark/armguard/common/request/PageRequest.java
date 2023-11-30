package vip.openpark.armguard.common.request;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class PageRequest<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	private Integer pageNum = 1;
	/**
	 * 每页数量
	 */
	private Integer pageSize = 20;

	/**
	 * 查询条件
	 */
	private T condition;

	private String sort; // 排序规则 DESC/ASC
	private String sortField; // 排序字段

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
			'}';
	}
}