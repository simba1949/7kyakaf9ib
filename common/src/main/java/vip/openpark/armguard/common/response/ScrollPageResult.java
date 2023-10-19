package vip.openpark.armguard.common.response;

import java.io.Serializable;

/**
 * 游标查询
 *
 * @author anthony
 * @version 2023/10/19 13:23
 */
public class ScrollPageResult<T> extends PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String scrollId; // 游标

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    @Override
    public String toString() {
        return "ScrollPageResult{" +
                "scrollId='" + scrollId + '\'' +
                "} " + super.toString();
    }
}