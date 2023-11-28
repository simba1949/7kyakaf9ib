package vip.openpark.armguard.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author anthony
 * @version 2023/11/28 15:31:19
 */
public class AssertUtils {

	private AssertUtils() {
	}

	/**
	 * 抛出异常
	 * - 如有必要，可将 IllegalArgumentException 换成自定义的异常
	 *
	 * @param message 提示信息
	 */
	public static void throwIAE(final String message) {
		throw new IllegalArgumentException(message);
	}

	/**
	 * 抛出异常
	 * - 如有必要，可将 NullPointerException 换成自定义的异常
	 *
	 * @param message 提示信息
	 */
	public static void throwNPE(final String message) {
		throw new NullPointerException(message);
	}


	/**
	 * 表达式结果必须为 true，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 */
	public static void isTrue(final boolean expression) {
		if (!expression) {
			throwIAE("The result of this expression is false, but it is required to be true.");
		}
	}

	/**
	 * 表达式结果必须为 true，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 * @param message    提示信息
	 */
	public static void isTrue(final boolean expression, final String message) {
		if (!expression) {
			throwIAE(message);
		}
	}

	/**
	 * 表达式结果必须为 true，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 * @param supplier   EX Supplier
	 * @param <EX>       泛型
	 */
	public static <EX extends RuntimeException> void isTrue(final boolean expression, final Supplier<EX> supplier) {
		if (!expression) {
			throw supplier.get();
		}
	}

	/**
	 * 表达式结果必须为 false，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 */
	public static void isFalse(final boolean expression) {
		if (expression) {
			throwIAE("The result of this expression is true, but it is required to be false.");
		}
	}

	/**
	 * 表达式结果必须为 false，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 * @param message    提示信息
	 */
	public static void isFalse(final boolean expression, final String message) {
		if (expression) {
			throwIAE(message);
		}
	}

	/**
	 * 表达式结果必须为 false，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 * @param supplier   EX Supplier
	 * @param <EX>       泛型
	 */
	public static <EX extends RuntimeException> void isFalse(final boolean expression, final Supplier<EX> supplier) {
		if (expression) {
			throw supplier.get();
		}
	}


	/**
	 * 对象类型不能为空，否则抛出异常
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 */
	public static <T> void notNull(final T obj) {
		if (ObjectUtils.isNull(obj)) {
			throwNPE("The object is null, but it is required not null.");
		}
	}

	/**
	 * 对象类型不能为空，否则抛出异常
	 *
	 * @param obj     目标对象
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T> void notNull(final T obj, final String message) {
		if (ObjectUtils.isNull(obj)) {
			throwNPE(message);
		}
	}

	/**
	 * 对象类型不能为空，否则抛出异常
	 *
	 * @param obj      目标对象
	 * @param supplier EX supplier
	 * @param <T>      泛型
	 * @param <EX>     泛型
	 */
	public static <T, EX extends RuntimeException> void notNull(final T obj, final Supplier<EX> supplier) {
		if (ObjectUtils.isNull(obj)) {
			throw supplier.get();
		}
	}

	/**
	 * 对象类型必须为空，否则抛出异常
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 */
	public static <T> void isNull(final T obj) {
		if (ObjectUtils.notNull(obj)) {
			throwIAE("The object is not null, but it is required null.");
		}
	}

	/**
	 * 对象类型必须为空，否则抛出异常
	 *
	 * @param obj     目标对象
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T> void isNull(final T obj, final String message) {
		if (ObjectUtils.notNull(obj)) {
			throwIAE(message);
		}
	}

	/**
	 * 对象类型必须为空，否则抛出异常
	 *
	 * @param obj      目标对象
	 * @param supplier EX supplier
	 * @param <T>      泛型
	 * @param <EX>     泛型
	 */
	public static <T, EX extends RuntimeException> void isNull(final T obj, final Supplier<EX> supplier) {
		if (ObjectUtils.notNull(obj)) {
			throw supplier.get();
		}
	}

	/**
	 * 字符串类型不能为空，否则抛出异常
	 *
	 * @param chars 目标对象
	 * @param <T>   泛型
	 */
	public static <T extends CharSequence> void notBlank(final T chars) {
		if (StringUtils.isBlank(chars)) {
			throwIAE("The chars is bank , but it is required not bank.");
		}
	}

	/**
	 * 字符串类型不能为空，否则抛出异常
	 *
	 * @param chars   目标对象
	 * @param message 提示信息
	 */
	public static <T extends CharSequence> void notBlank(final T chars, final String message) {
		if (StringUtils.isBlank(chars)) {
			throwIAE(message);
		}
	}

	/**
	 * 字符串类型不能为空，否则抛出异常
	 *
	 * @param chars    目标对象
	 * @param supplier EX Supplier
	 * @param <T>      泛型
	 * @param <EX>     泛型
	 */
	public static <T extends CharSequence, EX extends RuntimeException> void notBlank(final T chars, final Supplier<EX> supplier) {
		if (StringUtils.isBlank(chars)) {
			throw supplier.get();
		}
	}

	/**
	 * 字符串类型为空或者 null，否则抛出异常
	 *
	 * @param chars 目标对象
	 * @param <T>   泛型
	 */
	public static <T extends CharSequence> void isBlank(final T chars) {
		if (StringUtils.isNotBlank(chars)) {
			throwIAE("The chars is not bank , but it is required bank.");
		}
	}

	/**
	 * 字符串类型为空或者 null，否则抛出异常
	 *
	 * @param chars   目标对象
	 * @param message 提示信息
	 */
	public static <T extends CharSequence> void isBlank(final T chars, final String message) {
		if (StringUtils.isNotBlank(chars)) {
			throwIAE(message);
		}
	}

	/**
	 * 字符串类型为空或者 null，否则抛出异常
	 *
	 * @param chars    目标对象
	 * @param supplier EX Supplier
	 * @param <T>      泛型
	 * @param <EX>     泛型
	 */
	public static <T extends CharSequence, EX extends RuntimeException> void isBlank(final T chars, final Supplier<EX> supplier) {
		if (StringUtils.isNotBlank(chars)) {
			throw supplier.get();
		}
	}

	/**
	 * 数组不能为空，否则抛出异常
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 */
	public static <T> void notEmpty(final T[] obj) {
		if (CollectionUtils.isEmpty(obj)) {
			throwIAE("The array is empty , but it is required not empty.");
		}
	}

	/**
	 * 数组不能为空，否则抛出异常
	 *
	 * @param obj     目标对象
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T> void notEmpty(final T[] obj, final String message) {
		if (CollectionUtils.isEmpty(obj)) {
			throwIAE(message);
		}
	}

	/**
	 * 数组不能为空，否则抛出异常
	 *
	 * @param obj      目标对象
	 * @param supplier EX Supplier
	 * @param <T>      泛型
	 * @param <EX>     泛型
	 */
	public static <T, EX extends RuntimeException> void notEmpty(final T[] obj, final Supplier<EX> supplier) {
		if (CollectionUtils.isEmpty(obj)) {
			throw supplier.get();
		}
	}


	/**
	 * 数组必须为空，否则抛出异常
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 */
	public static <T> void isEmpty(final T[] obj) {
		if (CollectionUtils.nonEmpty(obj)) {
			throwIAE("The array is not empty , but it is required empty.");
		}
	}

	/**
	 * 数组必须为空，否则抛出异常
	 *
	 * @param obj     目标对象
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T> void isEmpty(final T[] obj, final String message) {
		if (CollectionUtils.nonEmpty(obj)) {
			throwIAE(message);
		}
	}

	/**
	 * 数组必须为空，否则抛出异常
	 *
	 * @param obj      目标对象
	 * @param supplier EX Supplier
	 * @param <T>      泛型
	 * @param <EX>     泛型
	 */
	public static <T, EX extends RuntimeException> void isEmpty(final T[] obj, final Supplier<EX> supplier) {
		if (CollectionUtils.nonEmpty(obj)) {
			throw supplier.get();
		}
	}

	/**
	 * 集合不能为空，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param <T>        泛型
	 */
	public static <T extends Collection<?>> void notEmpty(final T collection) {
		if (CollectionUtils.isEmpty(collection)) {
			throwIAE("The collection is empty , but it is required not empty.");
		}
	}

	/**
	 * 集合不能为空，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param message    提示信息
	 * @param <T>        泛型
	 */
	public static <T extends Collection<?>> void notEmpty(final T collection, final String message) {
		if (CollectionUtils.isEmpty(collection)) {
			throwIAE(message);
		}
	}

	/**
	 * 集合不能为空，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param supplier   EX Supplier
	 * @param <T>        泛型
	 * @param <EX>       泛型
	 */
	public static <T extends Collection<?>, EX extends RuntimeException> void notEmpty(final T collection, final Supplier<EX> supplier) {
		if (CollectionUtils.isEmpty(collection)) {
			throw supplier.get();
		}
	}

	/**
	 * 集合必须为空或者 null，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param <T>        泛型
	 */
	public static <T extends Collection<?>> void isEmpty(final T collection) {
		if (CollectionUtils.nonEmpty(collection)) {
			throwIAE("The collection is not empty , but it is required empty.");
		}
	}


	/**
	 * 集合必须为空或者 null，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param message    提示信息
	 * @param <T>        泛型
	 */
	public static <T extends Collection<?>> void isEmpty(final T collection, final String message) {
		if (CollectionUtils.nonEmpty(collection)) {
			throwIAE(message);
		}
	}

	/**
	 * 集合必须为空或者 null，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param supplier   EX Supplier
	 * @param <T>        泛型
	 * @param <EX>       泛型
	 */
	public static <T extends Collection<?>, EX extends RuntimeException> void isEmpty(final T collection, final Supplier<EX> supplier) {
		if (CollectionUtils.nonEmpty(collection)) {
			throw supplier.get();
		}
	}

	/**
	 * map集合不能为空，否则抛出异常
	 *
	 * @param map 目标集合
	 * @param <T> 泛型
	 */
	public static <T extends Map<?, ?>> void notEmpty(final T map) {
		if (MapUtils.isEmpty(map)) {
			throwIAE("The map is empty , but it is required not empty.");
		}
	}

	/**
	 * map集合不能为空，否则抛出异常
	 *
	 * @param map     目标集合
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T extends Map<?, ?>> void notEmpty(final T map, final String message) {
		if (MapUtils.isEmpty(map)) {
			throwIAE(message);
		}
	}

	/**
	 * map集合不能为空，否则抛出异常
	 *
	 * @param map      目标集合
	 * @param supplier EX Supplier
	 * @param <T>      泛型
	 * @param <EX>     泛型
	 */
	public static <T extends Map<?, ?>, EX extends RuntimeException> void notEmpty(final T map, final Supplier<EX> supplier) {
		if (MapUtils.isEmpty(map)) {
			throw supplier.get();
		}
	}

	/**
	 * map集合必须为空，否则抛出异常
	 *
	 * @param map 目标集合
	 * @param <T> 泛型
	 */
	public static <T extends Map<?, ?>> void isEmpty(final T map) {
		if (MapUtils.nonEmpty(map)) {
			throwIAE("The map is not empty , but it is required empty.");
		}
	}

	/**
	 * map集合必须为空，否则抛出异常
	 *
	 * @param map     目标集合
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T extends Map<?, ?>> void isEmpty(final T map, final String message) {
		if (MapUtils.nonEmpty(map)) {
			throwIAE(message);
		}
	}

	/**
	 * map集合必须为空，否则抛出异常
	 *
	 * @param map      目标集合
	 * @param supplier EX Supplier
	 * @param <T>      泛型
	 * @param <EX>     泛型
	 */
	public static <T extends Map<?, ?>, EX extends RuntimeException> void isEmpty(final T map, final Supplier<EX> supplier) {
		if (MapUtils.nonEmpty(map)) {
			throw supplier.get();
		}
	}
}