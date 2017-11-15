package com.r2d2.pecan.common.utils;

import org.apache.commons.lang.ObjectUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FormatUtil {
    //设置数值精度
    public static String toMoneyFormat(Double digit) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(digit);
    }

    //非数值字段的转化
    public static String tranferObjectToString(Object o) {
        return ObjectUtils.toString(o);
        //return o!=null?o.toString():"";
    }

    //将对象转换成数值字符串
    public static String tranferObjectToDoubleString(Object o) {
        return o != null ? FormatUtil.tranferDoubleNew(Double.parseDouble(o.toString())) : "0.00";
    }

    //将对象转化为Double类型
    public static Double tranferObjectToDouble(Object o) {
        return o != null ? Double.parseDouble(o.toString()) : 0.00;
    }

    //字符串转double
    public static Double tranferStringToDouble(String s) {
        return s.equals("") ? 0.00 : Double.parseDouble(s);
    }

    public static Double switchDouble(Object obj) {
        if (obj == null) {
            return 0.00;
        }
        return BigDecimalUtils.round(obj.toString(), 2);
    }


    private static final DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");

    //将Double对象转化为String类型,传入：1000000.11,输入效果为：1,000,000.11
    public static String tranferDoubleNew(Object o) {
        Double d = o != null ? Double.parseDouble(o.toString())/100.0 : 0.00;
        return fmt.format(d);
    }

    //将Object对象转化String类型，金额以万元为单位，保留四位有效数字
    public static String tranferStringNew(Object o) {
        Double d = o != null ? Double.parseDouble(o.toString()) : 0.0000;
        double dd = Math.round(d) / 10000.0;
        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format(dd);
    }

    /**
     * 元转分
     * @param amount
     * @return
     */
    public static String yuanToPoint(String amount) {
        if(amount ==null || "".equals(amount))
            amount = "0";
        Double amountD=Double.parseDouble(amount);
        amountD = amountD * 100;
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(amountD);
    }

    /**
     * 分转元（有逗号分隔）
     * @param amount
     * @return
     */
    public static String pointToYuan(String amount) {
        if(amount ==null || "".equals(amount))
            amount = "0";
        Double amountD = Double.parseDouble(amount)/100;
//        DecimalFormat df = new DecimalFormat("0.##");;//不用科学计数法
        DecimalFormat df = new DecimalFormat("#,#0.00");;//不用科学计数法
        return df.format(amountD);
    }
    /**
     * 分转元（没有逗号分隔）
     * @param amount
     * @return
     */
    public static String pointToYuanWithOutComma(String amount) {
        if(amount ==null || "".equals(amount))
            amount = "0";
        Double amountD = Double.parseDouble(amount)/100;
        DecimalFormat df = new DecimalFormat("0.##");;//不用科学计数法
//        DecimalFormat df = new DecimalFormat("#,#0.00");;//不用科学计数法
        return df.format(amountD);
    }

    public static void main(String[] args) {

        String s = "0.";
        List<String> list = new ArrayList();
//        for (int i = 1000; i < 2000; i++) {
        for (int i = 245445; i < 245666; i++) {

            String y = pointToYuan(Integer.toString(i));
            System.out.println("i = " + i + "  " + y);
            if (!y.endsWith(Integer.toString(i))){
                list.add(y);
            }
        }
        System.out.println("list = " + list);
    }
}
