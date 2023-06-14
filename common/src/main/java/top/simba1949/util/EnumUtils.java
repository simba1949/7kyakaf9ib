package top.simba1949.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author anthony
 * @date 2023/6/13
 */
public class EnumUtils {

    private EnumUtils() {
    }


    /**
     * 获取字符串类型的结果数据
     * @param cls
     * @param codeVal
     * @return
     */
    public static String getStr(final Class<?> cls, final Object codeVal){
        Object obj = get(cls, codeVal, String.class);
        if (null == obj){
            return null;
        }

        return obj.toString();
    }

    /**
     * 结果转换
     * @param cls
     * @param codeVal
     * @param resultCls
     * @param <T>
     * @return
     */
    public static <T> T get(final Class<?> cls, final Object codeVal, final Class<T> resultCls){
        Object obj = get(cls, "getCode", "getDesc", codeVal);
        if (null == obj){
            return null;
        }

        if (resultCls.equals(obj.getClass())) {
            return resultCls.cast(obj);
        }

        return null;
    }

    /**
     * 利用反射原理获取 codeVal 对应的其他字段属性（例如描述相关）
     * @param cls 枚举 Class
     * @param getCodeMethodName 获取 code 的方法
     * @param getDescMethodName 获取描述的方法
     * @param codeVal 对应的 code 值
     * @return 返回结果对象
     */
    public static Object get(final Class<?> cls,
                             final String getCodeMethodName,
                             final String getDescMethodName,
                             final Object codeVal){
        if (null == cls || !cls.isEnum() ||
                null == getCodeMethodName || getCodeMethodName.isEmpty() ||
                null == getDescMethodName || getDescMethodName.isEmpty()){
            return null;
        }

        return Arrays.stream(cls.getEnumConstants()) // 获取所有枚举值
                .filter(Objects::nonNull)
                .filter(anEnum -> {
                    try {
                        Method codeMethod = cls.getMethod(getCodeMethodName);
                        Object enumCodeVal = codeMethod.invoke(anEnum);

                        return null != enumCodeVal && enumCodeVal.equals(codeVal);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .map(anEnum -> {
                    try {
                        Method descMethod = cls.getMethod(getDescMethodName);
                        return descMethod.invoke(anEnum);
                    }catch (Exception e){
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
