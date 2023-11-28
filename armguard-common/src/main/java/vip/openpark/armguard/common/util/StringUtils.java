package vip.openpark.armguard.common.util;

/**
 * @author anthony
 * @version 2023/10/10 17:05
 */
public class StringUtils {

	private StringUtils() {
	}

	/**
	 * 判断字符类型是否为空
	 *
	 * @param chars 入参值
	 * @param <T>   字符类型
	 * @return true 表示是，false 表示否
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
	 *
	 * @param chars 入参值
	 * @param <T>   字符类型
	 * @return true 表示是，false 表示否
	 */
	public static <T extends CharSequence> boolean nonBlank(final T chars) {
		return !isBlank(chars);
	}
}