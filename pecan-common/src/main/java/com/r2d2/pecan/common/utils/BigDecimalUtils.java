package com.r2d2.pecan.common.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 一些Number类型的相加、相减、相除、相乘运算
 *
 * @author 欧西涛
 * @version 1.0
 * @Date 2014-11-03
 */
public class BigDecimalUtils {

    /**
     * 格式化金额格式
     */
    private static final DecimalFormat FMT = new DecimalFormat("##,###,###,###,##0.00");

    /**
     * 加法运算
     *
     * @param sum 加数
     * @param toSum 加数
     * @return 两个参数的和
     */
    public static String add(String sum, String toSum) {
        if (StringUtils.isEmpty(sum)) {
            sum = "0";
        }
        if (StringUtils.isEmpty(toSum)) {
            toSum = "0";
        }
        BigDecimal b1 = new BigDecimal(sum);
        BigDecimal b2 = new BigDecimal(toSum);
        return b1.add(b2).toString();
    }

    /**
     * 浮点数 加 小数位四舍五入精确计算 a+b
     *
     * @param a     加数
     * @param b     加数
     * @param scale 精确的小数位
     * @return double 相加之后的和
     */
    public static double preciseAdd(Double a, Double b, int scale) {
        if (null == b) {
            b = 0D;
        }
        if (null == a) {
            a = 0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(a));
        r = r.add(new BigDecimal(Double.toString(b)));
        return round(r.doubleValue(), scale);
    }

    /**
     * 减法运算
     *
     * @param sum 减数
     * @param toSum 被减数
     * @return 两个参数的差
     */
    public static String substract(String sum, String toSum) {
        if (StringUtils.isEmpty(sum)) {
            sum = "0";
        }
        if (StringUtils.isEmpty(toSum)) {
            toSum = "0";
        }
        BigDecimal b1 = new BigDecimal(sum);
        BigDecimal b2 = new BigDecimal(toSum);
        return b1.subtract(b2).toString();
    }

    /**
     * 浮点数 减 小数位四舍五入精确计算 sub-toSub
     *
     * @param sub     被减数
     * @param toSub     减数
     * @param scale 精确的小数位
     * @return 相减之后和
     */
    public static double preciseSub(Double sub, Double toSub, int scale) {
        if (null == toSub) {
            toSub = 0D;
        }
        if (null == sub) {
            sub = 0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(sub));
        r = r.subtract(new BigDecimal(Double.toString(toSub)));
        return round(r.doubleValue(), scale);
    }

    /**
     * 浮点数 乘 精确计算 mul*toMul
     *
     * @param mul 被乘数
     * @param toMul 乘数
     * @return double mul*b的结果
     */
    public static double preciseMul(Double mul, Double toMul) {
        if (null == toMul || null == mul) {
            return 0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(mul));
        r = r.multiply(new BigDecimal(Double.toString(toMul)));
        return r.doubleValue();
    }

    /**
     * 浮点数 乘 精确计算 mul*toMul
     *
     * @param mul
     * @param toMul
     * @return
     */
    public static String preciseMul(String mul, String toMul) {
        if (StringUtils.isEmpty(mul)) {
            mul = "0";
        }
        if (StringUtils.isEmpty(toMul)) {
            toMul = "0";
        }
        BigDecimal r = new BigDecimal(mul);
        r = r.multiply(new BigDecimal(toMul));
        return r.toString();
    }

    /**
     * 浮点数 乘 小数位四舍五入精确计算 mul*toMul
     *
     * @param mul
     * @param toMul
     * @param scale 精确的小数位
     * @return
     */
    public static double preciseMul(Double mul, Double toMul, int scale) {
        if (null == toMul || null == mul) {
            return 0D;
        }
        return round(
                new BigDecimal(Double.toString(mul)).multiply(
                        new BigDecimal(Double.toString(toMul))).doubleValue(),
                scale);
    }

    /**
     * 字符串 乘 小数位四舍五入精确计算 a*b
     *
     * @param a
     * @param b
     * @param scale 精确的小数位
     * @return
     */
    public static String preciseMul(String a, Double b, int scale) {
        if (null == b || null == a || Double.doubleToRawLongBits(b) == 0) {
            return "0";
        }
        double d = round(
                new BigDecimal(a).multiply(new BigDecimal(Double.toString(b)))
                        .doubleValue(), scale);
        return Double.toString(d);
    }

    /**
     * 浮点数 除 精确计算 a/b
     *
     * @param a 被除数
     * @param b 除数
     * @return double 相除之后的结果
     */
    public static double preciseDev(Double a, Double b) {
        if (null == b || Double.doubleToRawLongBits(b) == 0 || null == a) {
            return 0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(a));
        r = r.divide(new BigDecimal(Double.toString(b)), 6,
                BigDecimal.ROUND_HALF_UP);
        return r.doubleValue();
    }

    /**
     * 浮点数 除 小数位四舍五入精确计算 a/b
     *
     * @param a
     * @param b
     * @param scale 精确的小数位
     * @return
     */
    public static String preciseDev(Double a, Double b, int scale) {
        if (null == b || Double.doubleToRawLongBits(b) == 0 || null == a) {
            return "0";
        }
        BigDecimal r = new BigDecimal(Double.toString(a));
        r = r.divide(new BigDecimal(Double.toString(b)), scale,
                BigDecimal.ROUND_HALF_UP).setScale(scale);
        return r.toPlainString();
    }

    /**
     * 浮点数 除 小数位四舍五入精确计算 a/b
     *
     * @param a
     * @param b
     * @param scale 精确的小数位
     * @return
     */
    public static String preciseDev(String a, Double b, int scale) {
        if (null == b || Double.doubleToRawLongBits(b) == 0 || null == a) {
            return "0";
        }
        BigDecimal r = new BigDecimal(a);
        r = r.divide(new BigDecimal(Double.toString(b)), scale,
                BigDecimal.ROUND_HALF_UP).setScale(scale);
        return r.toPlainString();
    }

    /**
     * 比较两个值的大小
     *
     * @param
     * @param
     * @return -1 小于 num1 < num2 0 相等 num1 = num2 1 大于 num1 > num2
     */
    public static int isCompareTo(String num1, String num2) {
        if (StringUtils.isEmpty(num1)) {
            num1 = "0";
        }
        if (StringUtils.isEmpty(num2)) {
            num2 = "0";
        }
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.compareTo(b2);
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(String v, int scale) {
        if (null == v) {
            v = "0";
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = BigDecimal.ONE;
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(Double v, int scale) {
        if (null == v) {
            v = 0D;
        }
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将数字格式化，如：传入：1000000.11,输入效果为：1,000,000.11
     *
     * @param moeny
     * @param b
     * @return
     */
    public static String formattingMoeny(String moeny, Double b) {
        return FMT.format(Long.parseLong(moeny) / b);
    }

    /**
     * 将数字格式化，如：传入：1000000.11,输入效果为：1,000,000.11
     *
     * @param moeny
     * @param b
     * @return
     */
    public static String formattingMoeny(Double moeny, Double b) {
        return FMT.format(moeny / b);
    }

    /**
     * 将数字格式化，如：传入：1000000.11,输入效果为：1,000,000.11
     *
     * @param moeny
     * @param
     * @return
     */
    public static String formattingMoeny(Double moeny) {
        return FMT.format(moeny);
    }

    /**
     * 将数字格式化，如：传入：1000000.11,输入效果为：1,000,000.11
     *
     * @param moeny
     * @param
     * @return
     */
    public static String formattingMoeny(String moeny) {
        return FMT.format(Long.parseLong(moeny));
    }
}
