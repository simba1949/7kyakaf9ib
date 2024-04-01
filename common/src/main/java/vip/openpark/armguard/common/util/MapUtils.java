package vip.openpark.armguard.common.util;

import java.util.Map;

/**
 * @author anthony
 * @version 2023/10/10 17:34
 */
public class MapUtils {
    private MapUtils() {
    }

    /**
     * 判断map是否为空
     *
     * @param map 目标对象
     * @param <T> 泛型
     * @return true 表示是，false 表示否
     */
    public static <T extends Map<?, ?>> boolean isEmpty(final T map) {
        return null == map || map.isEmpty();
    }

    /**
     * 判断map是否不为空
     *
     * @param map 目标对象
     * @param <T> 泛型
     * @return true 表示是，false 表示否
     */
    public static <T extends Map<?, ?>> boolean nonEmpty(final T map) {
        return !isEmpty(map);
    }
}