package top.simba1949.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class PageUtils {

    private PageUtils() {
    }

    /**
     * 手工分页计算当前页数据
     *
     * @param dataList 数据源
     * @param pageNum  当前页，从1开始
     * @param pageSize 每页数量
     * @param <T>      泛型
     * @return 结果
     */
    public static <T> List<T> curPageDataList(final List<T> dataList,
                                              final Integer pageNum,
                                              final Integer pageSize) {
        if (null == dataList || dataList.size() == 0 ||
                null == pageNum || pageNum < 1 ||
                null == pageSize || pageSize < 1) {
            return Collections.emptyList();
        }

        // 总数量
        int total = dataList.size();
        // 分页计算总页数算法：总页数=（总数-1）/每页数量+1
        int totalPage = (total - 1) / pageSize + 1;

        // 查询页码大于总页码返回为空
        if (pageNum > totalPage) {
            return Collections.emptyList();
        }

        int start = (pageNum - 1) * pageSize;
        int end = Math.min(pageNum * pageSize, total);

        List<T> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(dataList.get(i));
        }

        return result;
    }
}