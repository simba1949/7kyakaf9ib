package top.simba1949.request;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pageSize;
    private Integer pageNum;

    private String sort; // 排序规则 DESC/ASC
    private String sortField; // 排序字段

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
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
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", sort='" + sort + '\'' +
                ", sortField='" + sortField + '\'' +
                '}';
    }
}