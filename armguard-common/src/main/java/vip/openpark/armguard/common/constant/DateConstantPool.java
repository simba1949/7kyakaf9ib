package vip.openpark.armguard.common.constant;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author anthony
 * @version 2023/6/13
 */
public interface DateConstantPool {
	String DATE_0 = "yyyy-MM-dd";
	String DATE_1 = "yyyy年MM月dd日";

	String TIME_0 = "HH:mm:ss";
	String TIME_1 = "HH时mm分ss秒";

	String DATE_TIME_0 = "yyyy-MM-dd HH:mm:ss";
	String DATE_TIME_1 = "yyyy年MM月dd日 HH时mm分ss秒";

	String DATE_TIME_UTC_0 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	String DATE_TIME_UTC_1 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	String DATE_TIME_UTC_2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	String DATE_TIME_UTC_3 = "yyyy-MM-dd'T'HH:mm:ss";

	long WEEK_CONVERT_DAY = 7L;
	long DAY_CONVERT_HOUR = 24L;
	long HOUR_CONVERT_MINUTE = 60L;
	long MINUTE_CONVERT_SECOND = 60L;
	long SECOND_CONVERT_MILLISECOND = 1000L;

	long DAY_CONVERT_MILLISECOND = 24 * 60 * 60 * 1000;
	long HOUR_CONVERT_MILLISECOND = 60 * 60 * 1000;
	long MINUTE_CONVERT_MILLISECOND = 60 * 1000;

	List<String> DATES = Arrays.asList(DATE_0, DATE_1);
	List<String> TIMES = Arrays.asList(TIME_0, TIME_1);
	List<String> DATE_TIMES = Arrays.asList(DATE_TIME_0, DATE_TIME_1);
	List<String> DATE_TIME_UTC = Arrays.asList(DATE_TIME_UTC_0, DATE_TIME_UTC_1, DATE_TIME_UTC_2, DATE_TIME_UTC_3);

	DateTimeFormatter LOCAL_DATE_FORMATTER_0 = DateTimeFormatter.ofPattern(DATE_0);
	DateTimeFormatter LOCAL_TIME_FORMATTER_0 = DateTimeFormatter.ofPattern(TIME_0);
	DateTimeFormatter LOCAL_DATE_TIME_FORMATTER_0 = DateTimeFormatter.ofPattern(DATE_TIME_0);

	SimpleDateFormat DATE_FORMATTER_0 = new SimpleDateFormat(DATE_0);
	SimpleDateFormat TIME_FORMATTER_0 = new SimpleDateFormat(TIME_0);
	SimpleDateFormat DATE_TIME_FORMATTER_0 = new SimpleDateFormat(DATE_TIME_0);
}