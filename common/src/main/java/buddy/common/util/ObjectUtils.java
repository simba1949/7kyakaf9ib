package buddy.common.util;

import java.math.BigDecimal;

/**
 * @author anthony
 * @version 2024/09/11
 * @since 2023/10/10 17:18
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

	/**
	 * 比较两个对象是否相等
	 *
	 * @param obj1 对象
	 * @param obj2 对象
	 * @return true 相等，false 不相等
	 */
	public static boolean isEquals(final Object obj1, final Object obj2) {
		if (null == obj1 || null == obj2) {
			return true;
		}

		// 数字比较
		if (obj1 instanceof Number && obj2 instanceof Number) {
			return new BigDecimal(obj1.toString()).compareTo(new BigDecimal(obj2.toString())) == 0;
		}

		return obj1.equals(obj2);
	}
}