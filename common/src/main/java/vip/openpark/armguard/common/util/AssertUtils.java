package vip.openpark.armguard.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * <title>断言工具类</title>
 * <div>
 *     <title>需要引入</title>
 *     <ol>
 *         <li>{@link ObjectUtils}</li>
 *         <li>{@link StringUtils}</li>
 *         <li>{@link CollectionUtils}</li>
 *         <li>{@link MapUtils}</li>
 *         <li>{@link ExceptionUtils}</li>
 *     </ol>
 * </div>
 *
 * @author anthony
 * @version 2023/11/28 18:13
 */
public class AssertUtils {

	private AssertUtils() {
	}


	/**
	 * 表达式结果必须为 true，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 */
	public static void isTrue(final boolean expression) {
		isTrue(expression, "The result of this expression is false, but it is required to be true.");
	}

	/**
	 * 表达式结果必须为 true，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 * @param message    提示信息
	 */
	public static void isTrue(final boolean expression, final String message) {
		isTrue(expression, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}

	/**
	 * 表达式结果必须为 false，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 */
	public static void isFalse(final boolean expression) {
		isFalse(expression, "The result of this expression is true, but it is required to be false.");
	}

	/**
	 * 表达式结果必须为 false，否则抛出异常
	 *
	 * @param expression 目标表达式结果
	 * @param message    提示信息
	 */
	public static void isFalse(final boolean expression, final String message) {
		isFalse(expression, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}


	/**
	 * 对象类型不能为空，否则抛出异常
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 */
	public static <T> void notNull(final T obj) {
		notNull(obj, "The object is null, but it is required not null.");
	}

	/**
	 * 对象类型不能为空，否则抛出异常
	 *
	 * @param obj     目标对象
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T> void notNull(final T obj, final String message) {
		notNull(obj, () -> new NullPointerException(message));
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
			throw ExceptionUtils.nullSafeGet(supplier);
		}
	}

	/**
	 * 对象类型必须为空，否则抛出异常
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 */
	public static <T> void isNull(final T obj) {
		isNull(obj, "The object is not null, but it is required null.");
	}

	/**
	 * 对象类型必须为空，否则抛出异常
	 *
	 * @param obj     目标对象
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T> void isNull(final T obj, final String message) {
		isNull(obj, () -> new IllegalArgumentException(message));
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
		if (ObjectUtils.nonNull(obj)) {
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}

	/**
	 * 字符串类型不能为空，否则抛出异常
	 *
	 * @param chars 目标对象
	 * @param <T>   泛型
	 */
	public static <T extends CharSequence> void notBlank(final T chars) {
		notBlank(chars, "The chars is bank , but it is required not bank.");
	}

	/**
	 * 字符串类型不能为空，否则抛出异常
	 *
	 * @param chars   目标对象
	 * @param message 提示信息
	 */
	public static <T extends CharSequence> void notBlank(final T chars, final String message) {
		notBlank(chars, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}

	/**
	 * 字符串类型为空或者 null，否则抛出异常
	 *
	 * @param chars 目标对象
	 * @param <T>   泛型
	 */
	public static <T extends CharSequence> void isBlank(final T chars) {
		isBlank(chars, "The chars is not bank , but it is required bank.");
	}

	/**
	 * 字符串类型为空或者 null，否则抛出异常
	 *
	 * @param chars   目标对象
	 * @param message 提示信息
	 */
	public static <T extends CharSequence> void isBlank(final T chars, final String message) {
		isBlank(chars, () -> new IllegalArgumentException(message));
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
		if (StringUtils.nonBlank(chars)) {
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}

	/**
	 * 数组不能为空，否则抛出异常
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 */
	public static <T> void notEmpty(final T[] obj) {
		notEmpty(obj, "The array is empty , but it is required not empty.");
	}

	/**
	 * 数组不能为空，否则抛出异常
	 *
	 * @param obj     目标对象
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T> void notEmpty(final T[] obj, final String message) {
		notEmpty(obj, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}


	/**
	 * 数组必须为空，否则抛出异常
	 *
	 * @param obj 目标对象
	 * @param <T> 泛型
	 */
	public static <T> void isEmpty(final T[] obj) {
		isEmpty(obj, "The array is not empty , but it is required empty.");
	}

	/**
	 * 数组必须为空，否则抛出异常
	 *
	 * @param obj     目标对象
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T> void isEmpty(final T[] obj, final String message) {
		isEmpty(obj, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}

	/**
	 * 集合不能为空，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param <T>        泛型
	 */
	public static <T extends Collection<?>> void notEmpty(final T collection) {
		notEmpty(collection, "The collection is empty , but it is required not empty.");
	}

	/**
	 * 集合不能为空，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param message    提示信息
	 * @param <T>        泛型
	 */
	public static <T extends Collection<?>> void notEmpty(final T collection, final String message) {
		notEmpty(collection, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}

	/**
	 * 集合必须为空或者 null，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param <T>        泛型
	 */
	public static <T extends Collection<?>> void isEmpty(final T collection) {
		isEmpty(collection, "The collection is not empty , but it is required empty.");
	}


	/**
	 * 集合必须为空或者 null，否则抛出异常
	 *
	 * @param collection 目标集合
	 * @param message    提示信息
	 * @param <T>        泛型
	 */
	public static <T extends Collection<?>> void isEmpty(final T collection, final String message) {
		isEmpty(collection, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}

	/**
	 * map集合不能为空，否则抛出异常
	 *
	 * @param map 目标集合
	 * @param <T> 泛型
	 */
	public static <T extends Map<?, ?>> void notEmpty(final T map) {
		notEmpty(map, "The map is empty , but it is required not empty.");
	}

	/**
	 * map集合不能为空，否则抛出异常
	 *
	 * @param map     目标集合
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T extends Map<?, ?>> void notEmpty(final T map, final String message) {
		notEmpty(map, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}

	/**
	 * map集合必须为空，否则抛出异常
	 *
	 * @param map 目标集合
	 * @param <T> 泛型
	 */
	public static <T extends Map<?, ?>> void isEmpty(final T map) {
		isEmpty(map, "The map is not empty , but it is required empty.");
	}

	/**
	 * map集合必须为空，否则抛出异常
	 *
	 * @param map     目标集合
	 * @param message 提示信息
	 * @param <T>     泛型
	 */
	public static <T extends Map<?, ?>> void isEmpty(final T map, final String message) {
		isEmpty(map, () -> new IllegalArgumentException(message));
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
			throw ExceptionUtils.iaeSafeGet(supplier);
		}
	}
}