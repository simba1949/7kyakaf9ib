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
	 * @param <T>   泛型
	 * @return true 表示是，false 表示否
	 */
	public static <T extends CharSequence> boolean isNotBlank(final T chars) {
		return !isBlank(chars);
	}

	/**
	 * 判断字符类型是否不为空
	 *
	 * @param chars 入参值
	 * @param <T>   泛型
	 * @return true 表示是，false 表示否
	 */
	public static <T extends CharSequence> boolean nonBlank(final T chars) {
		return !isBlank(chars);
	}

	/**
	 * 补填前缀
	 *
	 * @param src      字符串
	 * @param fillChar 补填字符串
	 * @param len      补填到长度
	 * @return 补填后的数据
	 */
	public static String fillPrefix(final String src, final Character fillChar, final int len) {
		return fill(src, fillChar, len, true);
	}

	/**
	 * 补填前缀
	 *
	 * @param src      字符串
	 * @param fillChar 补填字符串
	 * @param len      补填到长度
	 * @return 补填后的数据
	 */
	public static String fillSuffix(final String src, final Character fillChar, final int len) {
		return fill(src, fillChar, len, false);
	}

	/**
	 * 补填
	 *
	 * @param src      字符串
	 * @param fillChar 补填字符串
	 * @param len      补填到长度
	 * @param preFlag  前缀标识
	 * @return 补填后的数据
	 */
	public static String fill(final String src, final Character fillChar, final int len, final boolean preFlag) {
		if (null == src || src.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < len; i++) {
				builder.append(fillChar);
			}
			return builder.toString();
		}

		// 如果大于等于直接返回
		if (src.length() >= len) {
			return src;
		}

		StringBuilder builder = new StringBuilder(src);
		if (preFlag) {
			StringBuilder reverse = builder.reverse();
			while (builder.length() < len) {
				reverse.append(fillChar);
			}
			return reverse.reverse().toString();
		} else {
			while (builder.length() < len) {
				builder.append(fillChar);
			}
		}
		return builder.toString();
	}
}