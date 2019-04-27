package features.utils;

import java.math.BigDecimal;

public class NumberUtils {


    public static BigDecimal getBigDecimalFromLong(long longValue) {
        return BigDecimal.valueOf(longValue).divide(new BigDecimal((100)));
    }

}
