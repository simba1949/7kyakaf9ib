package buddy.common.constant.pool;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数值常量池
 *
 * @author anthony
 * @version 2024/09/11
 * @since 2023/06/14 11:20
 */
public interface NumberPool {
	byte BYTE_0 = (byte) 0;
	byte BYTE_1 = (byte) 1;

	int INT_0 = 0;
	int INT_1 = 1;

	BigDecimal BIG_DECIMAL_0 = BigDecimal.ZERO;
	BigDecimal BIG_DECIMAL_1 = BigDecimal.ONE;
	BigDecimal BIG_DECIMAL_100 = new BigDecimal(100);

	DecimalFormat DECIMAL_FORMAT_0 = new DecimalFormat("0%");
	DecimalFormat DECIMAL_FORMAT_1 = new DecimalFormat("0.0%");
	DecimalFormat DECIMAL_FORMAT_2 = new DecimalFormat("0.00%");
	DecimalFormat DECIMAL_FORMAT_4 = new DecimalFormat("0.0000%");
}