package vip.openpark.armguard.common.util;

import vip.openpark.armguard.common.constant.DatePatterPool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <title>日期工具类</title>
 *
 * <div>
 *     <title>需要引入</title>
 *     <ol>
 *         <li>{@link DatePatterPool}</li>
 *         <li>{@link StringUtils}</li>
 *     </ol>
 * </div>
 *
 * @author anthony
 * @version 2023/12/6 11:15
 */
public class DateUtils {

	private DateUtils() {
	}

	/**
	 * 格式化
	 *
	 * @param date 日期
	 * @return 结果
	 */
	public static String format(final Date date) {
		return format(date, DatePatterPool.DATE_TIME_0);
	}

	/**
	 * 格式化
	 *
	 * @param date      日期
	 * @param formatter 格式化
	 * @return 结果
	 */
	public static String format(final Date date, final String formatter) {
		if (null == date || null == formatter || formatter.isEmpty()) {
			return null;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
		return format(date, simpleDateFormat);
	}

	/**
	 * 格式化
	 *
	 * @param date      日期
	 * @param formatter 格式化
	 * @return 结果
	 */
	public synchronized static String format(final Date date, final SimpleDateFormat formatter) {
		if (null == date || null == formatter) {
			return null;
		}

		try {
			return formatter.format(date);
		} catch (Exception e) {
			// Consider printing the log
			return null;
		}
	}


	/**
	 * 解析日期字符串
	 *
	 * @param dateStr 原始日期字符串
	 * @return 结果
	 */
	public static Date parse(final String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}

		return Stream.of(DatePatterPool.DATES, DatePatterPool.TIMES, DatePatterPool.DATE_TIMES, DatePatterPool.DATE_TIME_UTC)
			       .flatMap(List::stream)
			       .map(DateUtils::parse)
			       .filter(Objects::nonNull)
			       .findFirst()
			       .orElse(null);
	}

	/**
	 * 解析日期字符串
	 *
	 * @param dateStr 原始日期字符串
	 * @return 结果
	 */
	public static Date parseDate(final String dateStr) {
		return parse(dateStr, DatePatterPool.DATE_0);
	}

	/**
	 * 解析日期时间字符串
	 *
	 * @param dateStr 原始日期时间字符串
	 * @return 结果
	 */
	public static Date parseDateTime(final String dateStr) {
		return parse(dateStr, DatePatterPool.DATE_TIME_0);
	}

	/**
	 * 解析日期字符串
	 *
	 * @param dateStr   原始日期字符串
	 * @param formatter 格式对象
	 * @return 结果
	 */
	public static Date parse(final String dateStr, final String formatter) {
		if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(formatter)) {
			return null;
		}

		return parse(dateStr, new SimpleDateFormat(formatter));
	}

	/**
	 * 解析日期字符串
	 *
	 * @param dateStr   原始日期字符串
	 * @param formatter 格式对象
	 * @return 结果
	 */
	public synchronized static Date parse(final String dateStr, final SimpleDateFormat formatter) {
		if (StringUtils.isBlank(dateStr) || null == formatter) {
			return null;
		}

		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			// Consider printing the log
			return null;
		}
	}
}