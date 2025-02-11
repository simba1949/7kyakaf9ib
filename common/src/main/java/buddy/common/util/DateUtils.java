package buddy.common.util;

import buddy.common.constant.pool.DatePool;

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
 * 日期工具类
 *
 * @author anthony
 * @version 2024/09/11
 * @since 2023/12/6 11:15
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
		return format(date, DatePool.DATE_TIME_0);
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

		return Stream.of(DatePool.DATES, DatePool.TIMES, DatePool.DATE_TIMES)
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
		return parse(dateStr, DatePool.DATE_0);
	}

	/**
	 * 解析日期时间字符串
	 *
	 * @param dateStr 原始日期时间字符串
	 * @return 结果
	 */
	public static Date parseDateTime(final String dateStr) {
		return parse(dateStr, DatePool.DATE_TIME_0);
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


	public static final ConcurrentHashMap<TimeUnit, BiFunction<Date, Date, Long>> BETWEEN_MAP = new ConcurrentHashMap<TimeUnit, BiFunction<Date, Date, Long>>() {
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

		return BETWEEN_MAP.get(timeUnit).apply(date0, date1);
	}

	private static long betweenDay(final Date date0, final Date date1) {
		return (date1.getTime() - date0.getTime()) / DatePool.DAY_CONVERT_MILLISECOND;
	}

	private static long betweenHour(final Date date0, final Date date1) {
		return (date1.getTime() - date0.getTime()) / DatePool.HOUR_CONVERT_MILLISECOND;
	}

	private static long betweenMinute(final Date date0, final Date date1) {
		return (date1.getTime() - date0.getTime()) / DatePool.MINUTE_CONVERT_MILLISECOND;
	}

	private static long betweenSecond(final Date date0, final Date date1) {
		return (date1.getTime() - date0.getTime()) / DatePool.SECOND_CONVERT_MILLISECOND;
	}


	public static final ConcurrentHashMap<TimeUnit, BiFunction<Date, Long, Date>> OFFSET_MAP =
		new ConcurrentHashMap<TimeUnit, BiFunction<Date, Long, Date>>() {
			private static final long serialVersionUID = 4689274915836322154L;

			{
				put(TimeUnit.DAYS, DateUtils::offsetDay);
				put(TimeUnit.HOURS, DateUtils::offsetHour);
				put(TimeUnit.MILLISECONDS, DateUtils::offsetMinute);
				put(TimeUnit.SECONDS, DateUtils::offsetSecond);
			}
		};

	/**
	 * 时间偏移
	 * <ol>
	 *     <li>offset 为正整数，向 srcDate 未来偏移</li>
	 *     <li>offset 为负整数，向 srcDate 过去偏移</li>
	 * </ol>
	 *
	 * @param srcDate  依据日期
	 * @param offset   偏移量
	 * @param timeUnit 偏移单位
	 * @return Date
	 */
	public static Date offset(final Date srcDate, final long offset, final TimeUnit timeUnit) {
		if (null == srcDate || null == timeUnit) {
			throw new NullPointerException("param not allow null");
		}

		// 无需偏移
		if (0 == offset) {
			return srcDate;
		}

		return OFFSET_MAP.get(timeUnit).apply(srcDate, offset);
	}

	private static Date offsetDay(Date date, long offset) {
		long resultTimestamp = date.getTime() + offset * DatePool.DAY_CONVERT_MILLISECOND;
		return new Date(resultTimestamp);
	}

	private static Date offsetHour(Date date, long offset) {
		long resultTimestamp = date.getTime() + offset * DatePool.HOUR_CONVERT_MILLISECOND;
		return new Date(resultTimestamp);
	}

	private static Date offsetMinute(Date date, long offset) {
		long resultTimestamp = date.getTime() + offset * DatePool.MINUTE_CONVERT_MILLISECOND;
		return new Date(resultTimestamp);
	}

	private static Date offsetSecond(Date date, long offset) {
		long resultTimestamp = date.getTime() + offset * DatePool.SECOND_CONVERT_MILLISECOND;
		return new Date(resultTimestamp);
	}
}