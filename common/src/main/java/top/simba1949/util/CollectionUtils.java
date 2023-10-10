package top.simba1949.util;

import java.util.Collection;

/**
 * @author anthony
 * @version 2023/10/10 17:11
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * 判断集合是否为空
     * @param collection 集合入参
     * @return true 表示空，false 表示非空
     * @param <T> 集合泛型
     */
    public static <T extends Collection<?>> boolean isEmpty(final T collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 判断数组是否为空
     * @param obj 数组入参
     * @return true 表示空，false 表示非空
     * @param <T> 数组泛型
     */
    public static <T> boolean isEmpty(final T[] obj) {
        if (null == obj) {
            return true;
        }
        return obj.length == 0;
    }
}
