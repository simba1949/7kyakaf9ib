package vip.openpark.armguard.common.util;

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
     * @param obj 目标对象
     * @param <T> 泛型
     * @return true 表示是，false 表示否
     */
    public static <T> boolean nonNull(final T obj) {
        return obj != null;
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 目标对象
     * @param <T> 泛型
     * @return true 表示是，false 表示否
     */
    public static <T> boolean isNull(final T obj) {
        return obj == null;
    }
}