package vip.openpark.armguard.common.util;

import vip.openpark.armguard.common.constant.DatePatterPool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@link DatePatterPool}
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
	public static String toStr(final Date date) {
		if (null == date) {
			return null;
		}

		return toStr(date, DatePatterPool.DATE_TIME_0);
	}

	/**
	 * 格式化
	 *
	 * @param date      日期
	 * @param formatter 格式化
	 * @return 结果
	 */
	public static String toStr(final Date date, final String formatter) {
		if (null == date || null == formatter || formatter.isEmpty()) {
			return null;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
		return toStr(date, simpleDateFormat);
	}

	/**
	 * 格式化
	 *
	 * @param date      日期
	 * @param formatter 格式化
	 * @return 结果
	 */
	public synchronized static String toStr(final Date date, final SimpleDateFormat formatter) {
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
}