package vip.openpark.armguard.common.util;

import java.util.function.Supplier;

/**
 * <title>异常工具类</title>
 *
 * @author anthony
 * @version 2023/12/7 10:00
 */
public class ExceptionUtils {

	private ExceptionUtils() {
	}

	/**
	 * 安全获取异常
	 *
	 * @param supplier EX Supplier
	 * @param <EX>     泛型
	 * @return 异常类
	 */
	public static <EX extends RuntimeException> RuntimeException iaeSafeGet(final Supplier<EX> supplier) {
		if (null == supplier || null == supplier.get()) {
			return new IllegalArgumentException();
		} else {
			return supplier.get();
		}
	}

	/**
	 * 安全获取异常
	 *
	 * @param supplier EX Supplier
	 * @param <EX>     泛型
	 * @return 异常类
	 */
	public static <EX extends RuntimeException> RuntimeException nullSafeGet(final Supplier<EX> supplier) {
		if (null == supplier || null == supplier.get()) {
			return new NullPointerException();
		} else {
			return supplier.get();
		}
	}
}