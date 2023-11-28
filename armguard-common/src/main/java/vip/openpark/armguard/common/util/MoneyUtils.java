package vip.openpark.armguard.common.util;

import java.util.*;

/**
 * 金钱数字转换成中文数值
 * 注意：最高支持：1京 减 0.01
 * <p>
 * 关于中文数值表示参考链接：<a href="https://baike.baidu.com/item/%E4%B8%AD%E6%96%87%E6%95%B0%E5%AD%97/2921705?fr=aladdin">...</a>
 *
 * <div>
 * 设计思路：将字符串通过小数点分割为整数部分和小数部分（小数部分取2位）
 * </div>
 *
 * <div>
 * 整数部分处理思路：
 * 数字字符串从后往前数，每8位一个单元，千百十万千百十个+单位
 * 每个单元，第三位是万位，需要特殊处理，
 * |->如果万前面存在数字，但是万位没有数值，需要添加上万，
 * |->如果万前面没有数值同时万没有数值，不用添加
 * </div>
 *
 * <div>
 * 小数部分处理思路：
 * 判断是否是否存在
 * |->存在，先取第一位小数位做角，然后判断长度大于等于2再取第二位小数做分，判断最终长度
 * |    |->等于0，返回`整`
 * |    |->不等于0，直接返回
 * |->不存在，返回`整`
 * </div>
 *
 * @author anthony
 * @version 2023/9/6
 */
public class MoneyUtils {

	private static final String NUMBER_ZERO_STR = "0";
	private static final String NUMBER_ONE_STR = "1";
	private static final String NUMBER_TWO_STR = "2";
	private static final String NUMBER_THREE_STR = "3";
	private static final String NUMBER_FOUR_STR = "4";
	private static final String NUMBER_FIVE_STR = "5";
	private static final String NUMBER_SIX_STR = "6";
	private static final String NUMBER_SEVEN_STR = "7";
	private static final String NUMBER_EIGHT_STR = "8";
	private static final String NUMBER_NINE_STR = "9";
	private static final String NUMBER_POINT_STR = ".";

	private static final String DELIMITER_DECIMAL_POINT = "\\.";
	private static final String DELIMITER_COMMA = ",";

	private static final String CN_NUMBER_ZERO_STR = "零";
	private static final String CN_NUMBER_ONE_STR = "壹";
	private static final String CN_NUMBER_TWO_STR = "贰";
	private static final String CN_NUMBER_THREE_STR = "叁";
	private static final String CN_NUMBER_FOUR_STR = "肆";
	private static final String CN_NUMBER_FIVE_STR = "伍";
	private static final String CN_NUMBER_SIX_STR = "陆";
	private static final String CN_NUMBER_SEVEN_STR = "柒";
	private static final String CN_NUMBER_EIGHT_STR = "捌";
	private static final String CN_NUMBER_NINE_STR = "玖";
	private static final String CN_NUMBER_TEN_STR = "拾";
	private static final String CN_NUMBER_HUNDRED_STR = "佰";
	private static final String CN_NUMBER_THOUSAND_STR = "仟";
	private static final String CN_NUMBER_TEN_THOUSAND_STR = "万";
	private static final String CN_NUMBER_BILLION_STR = "亿";
	private static final String CN_NUMBER_TRILLION_STR = "兆";

	private static final String RMB_UNIT_YUAN = "圆";
	private static final String RMB_UNIT_JIAO = "角";
	private static final String RMB_UNIT_FEN = "分";
	private static final String NO_POINT = "整";

	/**
	 * 数值与中文数字映射关系
	 */
	private static Map<String, String> numberMap = new HashMap<>(16);

	/**
	 * 长度与单位映射关系
	 */
	private static Map<Integer, String> unitMap = new HashMap<>(16);

	/**
	 * 从后往前数，每8个为单位，获取单位数值，千，百，十，万，千，百，十，一
	 */
	private static Map<Integer, String> unitMap4Digits = new HashMap<>(16);

	static {
		numberMap.put(NUMBER_ZERO_STR, CN_NUMBER_ZERO_STR);
		numberMap.put(NUMBER_ONE_STR, CN_NUMBER_ONE_STR);
		numberMap.put(NUMBER_TWO_STR, CN_NUMBER_TWO_STR);
		numberMap.put(NUMBER_THREE_STR, CN_NUMBER_THREE_STR);
		numberMap.put(NUMBER_FOUR_STR, CN_NUMBER_FOUR_STR);
		numberMap.put(NUMBER_FIVE_STR, CN_NUMBER_FIVE_STR);
		numberMap.put(NUMBER_SIX_STR, CN_NUMBER_SIX_STR);
		numberMap.put(NUMBER_SEVEN_STR, CN_NUMBER_SEVEN_STR);
		numberMap.put(NUMBER_EIGHT_STR, CN_NUMBER_EIGHT_STR);
		numberMap.put(NUMBER_NINE_STR, CN_NUMBER_NINE_STR);
	}

	static {
		unitMap4Digits.put(1, "");
		unitMap4Digits.put(2, CN_NUMBER_TEN_STR);
		unitMap4Digits.put(3, CN_NUMBER_HUNDRED_STR);
		unitMap4Digits.put(4, CN_NUMBER_THOUSAND_STR);
		unitMap4Digits.put(5, CN_NUMBER_TEN_THOUSAND_STR);

		unitMap4Digits.put(6, CN_NUMBER_TEN_STR);
		unitMap4Digits.put(7, CN_NUMBER_HUNDRED_STR);
		unitMap4Digits.put(8, CN_NUMBER_THOUSAND_STR);
	}

	static {
		// 圆
		unitMap.put(1, RMB_UNIT_YUAN);
		// 亿
		unitMap.put(2, CN_NUMBER_BILLION_STR);
		// 兆
		unitMap.put(3, CN_NUMBER_TRILLION_STR);
	}

	/**
	 * 钱币
	 * 数字转换成中文大写
	 * 目前最大支持一兆
	 *
	 * @return
	 */
	public static String moneyNumber2CnNumber(final String original) {
		if (null == original || original.trim().isEmpty()) {
			return "";
		}

		// beforePoint 表示整数部分，afterPoint表示小数部分
		String beforePoint, afterPoint = "";

		String originalTrim = original.replace(" ", "").trim();
		if (originalTrim.contains(NUMBER_POINT_STR)) {
			// 存在小数点
			String[] split = originalTrim.split(DELIMITER_DECIMAL_POINT);
			beforePoint = split[0].trim();
			afterPoint = split[1].trim();
		} else {
			// 不存在小数点
			beforePoint = originalTrim;
		}

		return integerDealWith(beforePoint) + decimalDealWith(afterPoint);
	}

	/**
	 * 整数部分处理方式
	 *
	 * @param integer
	 * @return
	 */
	private static String integerDealWith(String integer) {
		if (null == integer || integer.trim().isEmpty()) {
			return "";
		}

		// 从后往前数，每8位添加一个分割符号,
		List<String> list = new ArrayList<>();
		char[] chars = integer.trim().toCharArray();
		int j = 1;
		for (int i = chars.length - 1; i >= 0; i--) {
			list.add(String.valueOf(chars[i]));
			if (j % 8 == 0) {
				list.add(DELIMITER_COMMA);
			}
			j++;
		}
		Collections.reverse(list);

		// 获取带有分割符`,`的字符串
		StringBuilder builder = new StringBuilder();
		list.forEach(builder::append);
		String str = builder.toString();
		// 去掉头部逗号
		if (str.startsWith(DELIMITER_COMMA)) {
			str = str.replaceFirst(DELIMITER_COMMA, "");
		}

		// 每8位分割后处理
		StringBuilder resultBuilder = new StringBuilder();
		String[] split = str.split(DELIMITER_COMMA);
		int length = split.length;
		for (int i = 0; i < length; i++) {
			resultBuilder.append(integerDealWithCoreWithUnit(split[i], unitMap.get(length - i)));
		}

		return resultBuilder.toString();
	}

	/**
	 * 数字按照 `千万百万十万，万千百十个` 排序
	 *
	 * @param number
	 * @param unit   最后的单位
	 * @return
	 */
	private static String integerDealWithCoreWithUnit(String number, String unit) {
		if (null == number || number.trim().isEmpty() || null == unit) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		switch (number.length()) {
			case 7:
				number = "0" + number;
				break;
			case 6:
				number = "00" + number;
				break;
			case 5:
				number = "000" + number;
				break;
			case 4:
				number = "0000" + number;
				break;
			case 3:
				number = "00000" + number;
				break;
			case 2:
				number = "000000" + number;
				break;
			case 1:
				number = "0000000" + number;
				break;
		}
		int length = number.length();
		int j = length;
		for (int i = 0; i < length; i++) {
			String value = String.valueOf(number.charAt(i));
			if (!NUMBER_ZERO_STR.equals(value)) {
				builder.append(numberMap.get(value)).append(unitMap4Digits.get(j));
			} else {
				// 8位数，第四位数没有值但是前面有值
				if (i == 3 && builder.length() > 0) {
					builder.append(CN_NUMBER_TEN_THOUSAND_STR);
				}
			}
			j--;
		}
		// 添加最后的单位
		if (builder.length() > 0) {
			builder.append(unit);
		}

		return builder.toString();
	}

	/**
	 * 小数部分处理方式
	 *
	 * @param decimal
	 * @return
	 */
	private static String decimalDealWith(String decimal) {
		if (null == decimal || decimal.trim().isEmpty()) {
			return NO_POINT;
		}
		// 自取两位小数
		StringBuilder builder = new StringBuilder();
		// 角
		String decimalTrim = decimal.trim();
		String tenthsNumberStr = String.valueOf(decimalTrim.charAt(0));
		if (!NUMBER_ZERO_STR.equals(tenthsNumberStr)) {
			builder.append(numberMap.get(tenthsNumberStr)).append(RMB_UNIT_JIAO);
		}
		// 分
		if (decimalTrim.length() >= 2) {
			String percentileNumberStr = String.valueOf(decimalTrim.charAt(1));
			if (!NUMBER_ZERO_STR.equals(percentileNumberStr)) {
				builder.append(numberMap.get(percentileNumberStr)).append(RMB_UNIT_FEN);
			}
		}
		// 不存在长度返回`整`
		if (builder.length() == 0) {
			return NO_POINT;
		}
		return builder.toString();
	}

	private MoneyUtils() {
	}
}