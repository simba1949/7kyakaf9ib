package top.simba1949.util;

/**
 * @author anthony
 * @version 2023/10/10 17:18
 */
public class ObjectUtils {
    private ObjectUtils() {
    }

    /**
     * 判断对象是否不为空
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> boolean notNull(final T obj) {
        return obj != null;
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 入参
     * @param <T>
     * @return
     */
    public static <T> boolean isNull(final T obj) {
        return obj == null;
    }
}