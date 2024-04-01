package vip.openpark.armguard.common.util;

import java.util.Collection;

/**
 * <title>集合工具类</title>
 *
 * @author anthony
 * @version 2023/10/10 17:11
 */
public class CollectionUtils {

	private CollectionUtils() {
	}

	/**
	 * 判断集合是否为空
	 *
	 * @param collection 目标对象
	 * @param <T>        泛型
	 * @return true 表示空，false 表示非空
	 */
	public static <T extends Collection<?>> boolean isEmpty(final T collection) {
		return null == collection || collection.isEmpty();
	}

	/**
	 * 判断集合是否不为空
	 *
	 * @param collection 目标对象
	 * @param <T>        泛型
	 * @return true 表示非空，false 表示空
	 */
	public static <T extends Collection<?>> boolean nonEmpty(final T collection) {
		return !isEmpty(collection);
	}

	/**
	 * 判断数组是否为空
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 * @return true 表示空，false 表示非空
	 */
	public static <T> boolean isEmpty(final T[] obj) {
		if (null == obj) {
			return true;
		}

		return obj.length == 0;
	}

	/**
	 * 判断数组是否不为空
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 * @return true 表示非空，false 表示空
	 */
	public static <T> boolean nonEmpty(final T[] obj) {
		return !isEmpty(obj);
	}
}