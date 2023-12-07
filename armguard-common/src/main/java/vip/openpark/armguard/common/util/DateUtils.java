package vip.openpark.armguard.common.util;

import vip.openpark.armguard.common.constant.DateConstantPool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * <title>日期工具类</title>
 *
 * <div>
 *     <title>需要引入</title>
 *     <ol>
 *         <li>{@link DateConstantPool}</li>
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
		return format(date, DateConstantPool.DATE_TIME_0);
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

		return Stream.of(DateConstantPool.DATES, DateConstantPool.TIMES, DateConstantPool.DATE_TIMES, DateConstantPool.DATE_TIME_UTC)
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
		return parse(dateStr, DateConstantPool.DATE_0);
	}

	/**
	 * 解析日期时间字符串
	 *
	 * @param dateStr 原始日期时间字符串
	 * @return 结果
	 */
	public static Date parseDateTime(final String dateStr) {
		return parse(dateStr, DateConstantPool.DATE_TIME_0);
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


	public static final ConcurrentHashMap<TimeUnit, BiFunction<Date, Date, Long>> MAP = new ConcurrentHashMap<TimeUnit, BiFunction<Date, Date, Long>>() {
		private static final long serialVersionUID = 4689274915836322154L;

		{
			put(TimeUnit.DAYS, DateUtils::betweenDay);
			put(TimeUnit.HOURS, DateUtils::betweenHour);
			put(TimeUnit.MINUTES, DateUtils::betweenMinute);
			put(TimeUnit.SECONDS, DateUtils::betweenSecond);
		}
	};

	/**
	 * 判断时间间隔
	 *
	 * @param date0    日期0
	 * @param date1    日期1
	 * @param timeUnit 时间间隔单位
	 * @return 结果
	 */
	public static long between(final Date date0, final Date date1, final TimeUnit timeUnit) {
		if (null == date0 || null == date1 || null == timeUnit) {
			throw new NullPointerException("param not allow null");
		}

		return MAP.get(timeUnit).apply(date0, date1);
	}

	private static long betweenDay(final Date date0, final Date date1) {
		return (date1.getTime() - date0.getTime()) / DateConstantPool.DAY_CONVERT_MILLISECOND;
	}

	private static long betweenHour(final Date date0, final Date date1) {
		return (date1.getTime() - date0.getTime()) / DateConstantPool.HOUR_CONVERT_MILLISECOND;
	}

	private static long betweenMinute(final Date date0, final Date date1) {
		return (date1.getTime() - date0.getTime()) / DateConstantPool.MINUTE_CONVERT_MILLISECOND;
	}

	private static long betweenSecond(final Date date0, final Date date1) {
		return (date1.getTime() - date0.getTime()) / DateConstantPool.SECOND_CONVERT_MILLISECOND;
	}
}