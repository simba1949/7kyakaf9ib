package top.simba1949.util;

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
     * @param map
     * @param <T>
     * @return
     */
    public static <T extends Map<?, ?>> boolean isEmpty(final T map) {
        if (null == map || map.isEmpty()) {
            return true;
        }
        return false;
    }

    public static <T extends Map<?, ?>> boolean notEmpty(final T map) {
        return !isEmpty(map);
    }
}
