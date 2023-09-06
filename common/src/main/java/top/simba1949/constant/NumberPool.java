package top.simba1949.constant;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author anthony
 * @date 2023/6/13
 */
public interface NumberPool {
    byte BYTE_0 = (byte)0;
    byte BYTE_1 = (byte)1;

    int INT_0 = 0;
    int INT_1 = 1;

    BigDecimal BD_0 = BigDecimal.ZERO;
    BigDecimal BD_1 = BigDecimal.ONE;
    BigDecimal BD_100 = new BigDecimal(100);

    DecimalFormat DF_0 = new DecimalFormat("0%");
    DecimalFormat DF_1 = new DecimalFormat("0.0%");
    DecimalFormat DF_2 = new DecimalFormat("0.00%");
}
