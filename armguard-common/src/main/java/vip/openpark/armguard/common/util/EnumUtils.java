package vip.openpark.armguard.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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
     * 校验枚举值是否合法
     *
     * @param cls     枚举 Class
     * @param codeVal 对应的 code 值
     * @return true 表示合法，false 表示不合法
     */
    public static <E extends Enum<E>> boolean checkLegal(final Class<E> cls, final Object codeVal) {
        return checkLegal(cls, "getCode", codeVal);
    }

    /**
     * 校验枚举值是否合法
     *
     * @param cls               枚举 Class
     * @param getCodeMethodName 获取 code 方法
     * @param codeVal           对应的 code 值
     * @return true 表示合法，false 表示不合法
     */
    public static <E extends Enum<E>> boolean checkLegal(final Class<E> cls,
                                                         final String getCodeMethodName,
                                                         final Object codeVal) {
        if (null == cls || !cls.isEnum() ||
                null == getCodeMethodName || getCodeMethodName.isEmpty()) {
            return false;
        }

        try {
            Method method = cls.getMethod(getCodeMethodName);

            return Arrays.stream(cls.getEnumConstants())
                    .anyMatch(anEnum -> {
                        try {
                            // 获取每个枚举对应方法的值
                            Object enumCodeVal = method.invoke(anEnum);

                            return (null == enumCodeVal && null == codeVal) ||
                                    (null != enumCodeVal && enumCodeVal.equals(codeVal));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            // Consider printing the log
                            return false;
                        }
                    });
        } catch (Exception e) {
            // Consider printing the log
            return false;
        }
    }

    /**
     * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
     *
     * @param cls     枚举 Class
     * @param codeVal 对应的 code 值
     * @return 返回字符串对象
     */
    public static <E extends Enum<E>> String getStr(final Class<E> cls,
                                                    final Object codeVal) {
        return getVal(cls, codeVal, String.class);
    }

    /**
     * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
     *
     * @param cls       枚举 Class
     * @param codeVal   对应的 code 值
     * @param resultCls 返回结果对象
     * @param <E>       枚举泛型
     * @param <R>       结果泛型
     * @return 返回结果对象
     */
    public static <E extends Enum<E>, R> R getVal(final Class<E> cls, final Object codeVal, final Class<R> resultCls) {
        return getVal(cls, "getCode", codeVal, "getDesc", resultCls);
    }

    /**
     * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
     *
     * @param cls               枚举 Class
     * @param getCodeMethodName 获取 code 的方法
     * @param codeVal           对应的 code 值
     * @param getDescMethodName 获取描述的方法
     * @param resultCls         结果的 Class 对象
     * @param <E>               枚举泛型
     * @param <R>               结果的泛型
     * @return 返回结果对象
     */
    public static <E extends Enum<E>, R> R getVal(final Class<E> cls,
                                                  final String getCodeMethodName,
                                                  final Object codeVal,
                                                  final String getDescMethodName,
                                                  final Class<R> resultCls) {
        // 判断 cls 必须是枚举类型
        if (null == cls || !cls.isEnum() ||
                null == getCodeMethodName || getCodeMethodName.isEmpty() ||
                null == getDescMethodName || getDescMethodName.isEmpty() ||
                null == resultCls) {
            return null;
        }

        try {
            E targetEnum = getEnum(cls, getCodeMethodName, codeVal);
            if (null == targetEnum) {
                return null;
            }

            // 获取枚举对应的描述值
            Method descMethod = cls.getMethod(getDescMethodName);
            Object obj = descMethod.invoke(targetEnum);
            if (null != obj && resultCls.equals(obj.getClass())) {
                // 将对象转换为此Class对象表示的类或接口
                return resultCls.cast(obj);
            }

            // 无法转换对象
            return null;
        } catch (Exception e) {
            // Consider printing the log
            return null;
        }
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

            return Arrays.stream(cls.getEnumConstants()) // 获取所有枚举值
                    .filter(anEnum -> {
                        try {
                            Object enumCodeVal = codeMethod.invoke(anEnum);
                            return (null == enumCodeVal && null == codeVal) ||
                                    (null != enumCodeVal && enumCodeVal.equals(codeVal));
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