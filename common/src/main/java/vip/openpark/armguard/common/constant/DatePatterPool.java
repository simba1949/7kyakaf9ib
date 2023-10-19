package vip.openpark.armguard.common.constant;

/**
 * @author anthony
 * @date 2023/6/13
 */
public interface DatePatterPool {
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
}