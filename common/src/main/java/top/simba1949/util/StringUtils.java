package top.simba1949.util;

/**
 * @author anthony
 * @version 2023/10/10 17:05
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * 判断字符类型是否为空
     * @param chars 入参值
     * @return true 表示空，false 表示非空
     * @param <T> 字符类型
     */
    public static <T extends CharSequence> boolean isBlank(final T chars) {
        int strLen;
        if (chars == null || (strLen = chars.length()) == 0) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(chars.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符类型是否不为空
     * @param chars 入参值
     * @return true 表示空，false 表示非空
     * @param <T> 字符类型
     */
    public static <T extends CharSequence> boolean isNotBlank(final T chars) {
        return !isBlank(chars);
    }
}
