package org.github.omnbmh.cobra.commons.utils;

import java.math.BigDecimal;

/**
 * BigDecimal 工具类
 * author: cobra
 */
public final class BigDecimalUtils {

    /**
     * 两个BigDecimal比较
     * return -1 0 1
     */
    public static int compare(BigDecimal one, BigDecimal two) {
        return one.compareTo(two);
    }

    /**
     * 小于
     */
    public static boolean lessThan(BigDecimal one, BigDecimal two) {
        return compare(one, two) == -1;
    }

    /**
     * 等于
     */
    public static boolean equalTo(BigDecimal one, BigDecimal two) {
        return compare(one, two) == 0;
    }

    /**
     * 大于
     */
    public static boolean greaterThan(BigDecimal one, BigDecimal two) {
        return compare(one, two) == 1;
    }

    /**
     * 小于等于
     */
    public static boolean lessThanOrEqual(BigDecimal one, BigDecimal two) {
        return lessThan(one, two) || equalTo(one, two);
    }

    /**
     * 大于等于
     */
    public static boolean greaterThanOrEqual(BigDecimal one, BigDecimal two) {
        return greaterThan(one, two) || equalTo(one, two);
    }
}
