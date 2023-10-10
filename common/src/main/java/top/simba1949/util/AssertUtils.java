package top.simba1949.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class AssertUtils {

    private AssertUtils() {
    }

    /**
     * 抛出异常
     * - 如有必要，可将 IllegalArgumentException 换成自定义的异常
     *
     * @param message
     */
    public static void throwIAE(final String message) {
        throw new IllegalArgumentException(message);
    }

    /**
     * 抛出异常
     * - 如有必要，可将 NullPointerException 换成自定义的异常
     *
     * @param message
     */
    public static void throwNPE(final String message) {
        throw new NullPointerException(message);
    }

    /**
     * 对象类型不能为空，否则抛出异常
     *
     * @param obj
     * @param message
     * @param <T>
     */
    public static <T> void notNull(final T obj, final String message) {
        if (ObjectUtils.isNull(obj)) {
            throwNPE(message);
        }
    }

    /**
     * 对象类型必须为空，否则抛出异常
     *
     * @param obj
     * @param message
     * @param <T>
     */
    public static <T> void isNull(final T obj, final String message) {
        if (ObjectUtils.notNull(obj)) {
            throwIAE(message);
        }
    }

    /**
     * 字符串类型不能为空，否则抛出异常
     *
     * @param chars
     * @param message
     */
    public static <T extends CharSequence> void notBlank(final T chars, final String message) {
        if (StringUtils.isBlank(chars)) {
            throwIAE(message);
        }
    }

    /**
     * 字符串类型为空或者 null，否则抛出异常
     *
     * @param chars
     * @param message
     */
    public static <T extends CharSequence> void isBlank(final T chars, final String message) {
        if (!StringUtils.isBlank(chars)) {
            throwIAE(message);
        }
    }

    /**
     * 表达式结果必须为 true，否则抛出异常
     *
     * @param expression
     * @param message
     */
    public static void isTrue(final boolean expression, final String message) {
        if (!expression) {
            throwIAE(message);
        }
    }

    /**
     * 表达式结果必须为 false，否则抛出异常
     *
     * @param expression
     * @param message
     */
    public static void isFalse(final boolean expression, final String message) {
        if (expression) {
            throwIAE(message);
        }
    }

    /**
     * 数组不能为空，否则抛出异常
     *
     * @param obj
     * @param message
     * @param <T>
     */
    public static <T> void notEmpty(final T[] obj, final String message) {
        if (CollectionUtils.isEmpty(obj)) {
            throwIAE(message);
        }
    }

    /**
     * 数组必须为空，否则抛出异常
     *
     * @param obj
     * @param message
     * @param <T>
     */
    public static <T> void isEmpty(final T[] obj, final String message) {
        if (!CollectionUtils.isEmpty(obj)) {
            throwIAE(message);
        }
    }

    /**
     * 集合不能为空，否则抛出异常
     *
     * @param collection
     * @param message
     * @param <T>
     */
    public static <T extends Collection<?>> void notEmpty(final T collection, final String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throwIAE(message);
        }
    }

    /**
     * 集合必须为空或者 null，否则抛出异常
     *
     * @param collection
     * @param message
     * @param <T>
     */
    public static <T extends Collection<?>> void isEmpty(final T collection, final String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throwIAE(message);
        }
    }

    /**
     * map集合不能为空，否则抛出异常
     *
     * @param map
     * @param message
     * @param <T>
     */
    public static <T extends Map<?, ?>> void notEmpty(final T map, final String message) {
        if (MapUtils.isEmpty(map)) {
            throwIAE(message);
        }
    }

    /**
     * map集合必须为空，否则抛出异常
     *
     * @param map
     * @param message
     * @param <T>
     */
    public static <T extends Map<?, ?>> void isEmpty(final T map, final String message) {
        if (MapUtils.notEmpty(map)) {
            throwIAE(message);
        }
    }
}