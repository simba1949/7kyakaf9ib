package top.simba1949.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * <title>枚举工具类</title>
 * <body>
 *     <div>
 *         <p>数据类型相同（基本类型对应包装类也算相同），可以获取对应的值数据</p>
 *         <p>codeVal需要保证在枚举中是唯一的，只可以存在一个 null</p>
 *     </div>
 * </body>
 *
 * @author anthony
 * @version 2023/6/13
 */
public class EnumUtils {

    private EnumUtils() {
    }

    /**
     * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
     *
     * @param cls     枚举 Class
     * @param codeVal 对应的 code 值
     * @return 返回字符串对象
     */
    public static String getVal(final Class<? extends Enum<?>> cls, final Object codeVal) {
        return getVal(cls, codeVal, String.class);
    }

    /**
     * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
     *
     * @param cls       枚举 Class
     * @param codeVal   对应的 code 值
     * @param resultCls 结果的 Class 对象
     * @param <R>       结果的泛型
     * @return 返回结果对象
     */
    public static <R> R getVal(final Class<? extends Enum<?>> cls, final Object codeVal, final Class<R> resultCls) {
        return getVal(cls, "getCode", "getDesc", codeVal, resultCls);
    }

    /**
     * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
     *
     * @param cls               枚举 Class
     * @param getCodeMethodName 获取 code 的方法
     * @param getDescMethodName 获取描述的方法
     * @param codeVal           对应的 code 值
     * @param resultCls         结果的 Class 对象
     * @param <R>               结果的泛型
     * @return 返回结果对象
     */
    public static <R> R getVal(final Class<? extends Enum<?>> cls,
                               final String getCodeMethodName,
                               final String getDescMethodName,
                               final Object codeVal,
                               final Class<R> resultCls) {
        // 判断 cls 必须是枚举类型
        if (null == cls || !cls.isEnum() ||
                null == getCodeMethodName || getCodeMethodName.isEmpty() ||
                null == getDescMethodName || getDescMethodName.isEmpty() ||
                null == resultCls) {
            return null;
        }

        return Arrays.stream(cls.getEnumConstants()) // 获取所有枚举值
                .filter(Objects::nonNull)
                .filter(anEnum -> {
                    try {
                        Method codeMethod = cls.getMethod(getCodeMethodName);
                        if (null == codeMethod) { // 找不到对应的方法，直接返回 false
                            return false;
                        }

                        Object enumCodeVal = codeMethod.invoke(anEnum);
                        if (null == enumCodeVal && null == codeVal) {
                            return true;
                        }

                        return null != enumCodeVal && enumCodeVal.equals(codeVal);
                    } catch (Exception e) {
                        // Consider printing the log
                        return false;
                    }
                })
                .map(anEnum -> {
                    try {
                        Method descMethod = cls.getMethod(getDescMethodName);
                        if (null == descMethod) { // 找不到对应的方法，直接返回
                            return null;
                        }

                        Object obj = descMethod.invoke(anEnum);
                        if (null != obj && resultCls.equals(obj.getClass())) {
                            // 将对象转换为此Class对象表示的类或接口
                            return resultCls.cast(obj);
                        }

                        // 无法转换对象
                        // Consider printing the log
                        return null;
                    } catch (Exception e) {
                        // Consider printing the log
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }


    /**
     * 获取枚举
     *
     * @param cls     枚举 Class
     * @param codeVal 对应的 code 值
     * @param <E>     结果的泛型
     * @return 结果枚举
     */
    public static <E extends Enum<E>> E getEnum(final Class<E> cls,
                                                final Object codeVal) {
        return getEnum(cls, "getCode", codeVal);
    }

    /**
     * 获取枚举
     *
     * @param cls     枚举 Class
     * @param codeVal 对应的 code 值
     * @param <E>     结果的泛型
     * @return 结果枚举
     */
    public static <E extends Enum<E>> E getEnum(final Class<E> cls,
                                                final String getCodeMethodName,
                                                final Object codeVal) {
        // 判断 cls 必须是枚举类型
        if (null == cls || !cls.isEnum() ||
                null == getCodeMethodName || getCodeMethodName.isEmpty()) {
            return null;
        }

        try {
            Method codeMethod = cls.getMethod(getCodeMethodName);
            if (null == codeMethod) { // 找不到对应的方法，直接返回
                return null;
            }

            return Arrays.stream(cls.getEnumConstants()) // 获取所有枚举值
                    .filter(anEnum -> {
                        try {
                            Object value = codeMethod.invoke(anEnum);
                            if (null == value && null == codeVal) {
                                return true;
                            }

                            return null != value && value.equals(codeVal);
                        } catch (Exception e) {
                            // Consider printing the log
                            return false;
                        }
                    })
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            // Consider printing the log
            return null;
        }
    }
}