package buddy.common.util;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 枚举工具类
 * <p>
 * 数据类型相同（基本类型对应包装类也算相同），可以获取对应的值数据
 * codeVal需要保证在枚举中是唯一的，只可以存在一个 null
 * </p>
 *
 * @author anthony
 * @version 2024/09/11
 * @since 2023/10/10 17:02
 */
public class EnumUtils {
	private EnumUtils() {
	}

	/**
	 * 校验枚举值是否合法
	 *
	 * @param clazz   枚举 Class
	 * @param codeVal 对应的 code 值
	 * @return true 表示合法，false 表示不合法
	 */
	public static <E extends Enum<E>> boolean isLegal(final Class<E> clazz, final Object codeVal) {
		return isLegal(clazz, "getCode", codeVal);
	}

	/**
	 * 校验枚举值是否合法
	 *
	 * @param clazz          枚举 Class
	 * @param codeMethodName 获取 code 方法
	 * @param codeVal        对应的 code 值
	 * @return true 表示合法，false 表示不合法
	 */
	public static <E extends Enum<E>> boolean isLegal(final Class<E> clazz,
	                                                  final String codeMethodName,
	                                                  final Object codeVal) {
		if (null == clazz || !clazz.isEnum() ||
			null == codeMethodName || codeMethodName.isEmpty()) {
			return false;
		}

		E anEnum = getEnum(clazz, codeMethodName, codeVal);

		return null != anEnum;
	}

	/**
	 * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
	 *
	 * @param clazz   枚举 Class
	 * @param codeVal 对应的 code 值
	 * @return 返回字符串对象
	 */
	public static <E extends Enum<E>> String getStrVal(final Class<E> clazz,
	                                                   final Object codeVal) {
		return getVal(clazz, codeVal, String.class);
	}

	/**
	 * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
	 *
	 * @param clazz     枚举 Class
	 * @param codeVal   对应的 code 值
	 * @param resultCls 返回结果对象
	 * @param <E>       枚举泛型
	 * @param <R>       结果泛型
	 * @return 返回结果对象
	 */
	public static <E extends Enum<E>, R> R getVal(final Class<E> clazz,
	                                              final Object codeVal,
	                                              final Class<R> resultCls) {
		return getVal(clazz, "getCode", codeVal, "getDesc", resultCls);
	}

	/**
	 * 利用反射原理获取 codeVal 对应的其他字段属性值（例如描述相关）
	 *
	 * @param clazz          枚举 Class
	 * @param codeMethodName 获取 code 的方法
	 * @param codeVal        对应的 code 值
	 * @param descMethodName 获取描述的方法
	 * @param resultCls      结果的 Class 对象
	 * @param <E>            枚举泛型
	 * @param <R>            结果的泛型
	 * @return 返回结果对象
	 */
	public static <E extends Enum<E>, R> R getVal(final Class<E> clazz,
	                                              final String codeMethodName,
	                                              final Object codeVal,
	                                              final String descMethodName,
	                                              final Class<R> resultCls) {
		// 判断 clazz 必须是枚举类型
		if (null == clazz || !clazz.isEnum() ||
			null == codeMethodName || codeMethodName.isEmpty() ||
			null == descMethodName || descMethodName.isEmpty() ||
			null == resultCls) {
			return null;
		}

		try {
			E targetEnum = getEnum(clazz, codeMethodName, codeVal);
			if (null == targetEnum) {
				return null;
			}

			// 获取枚举对应的描述值
			Method descMethod = clazz.getMethod(descMethodName);
			descMethod.setAccessible(true);

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
	 * @param clazz   枚举 Class
	 * @param codeVal 对应的 code 值
	 * @param <E>     结果的泛型
	 * @return 结果枚举
	 */
	public static <E extends Enum<E>> E getEnum(final Class<E> clazz,
	                                            final Object codeVal) {
		return getEnum(clazz, "getCode", codeVal);
	}

	/**
	 * 获取枚举
	 *
	 * @param clazz          枚举 Class
	 * @param codeMethodName 获取 code 的方法
	 * @param codeVal        对应的 code 值
	 * @param <E>            结果的泛型
	 * @return 结果枚举
	 */
	public static <E extends Enum<E>> E getEnum(final Class<E> clazz,
	                                            final String codeMethodName,
	                                            final Object codeVal) {
		// 判断 clazz 必须是枚举类型
		if (null == clazz || !clazz.isEnum() ||
			null == codeMethodName || codeMethodName.isEmpty()) {
			return null;
		}

		try {
			Method codeMethod = clazz.getMethod(codeMethodName);
			codeMethod.setAccessible(true);

			return Arrays
				.stream(clazz.getEnumConstants()) // 获取所有枚举值
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