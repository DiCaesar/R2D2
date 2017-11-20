package com.r2d2.pecan.common.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * <p>
 * 1、获取当前日期
 * 2、获取当前时间 格式： yyyyMMddHHmmss
 * 3、获取当前时间 格式： 自定义
 * 4、将字符串转换成固定格式时间
 * 5、将字符串转换成固定格式时间
 * 6、Date类型转换String 格式：yyyyMMdd
 * 7、Date类型转换String 格式：自定义
 * 8、增加时间
 * 9、按秒偏移,根据{@code source}得到{@code seconds}秒之后的日期<Br>
 * 10、根据传入的时分秒毫秒获取固定的当日时间点
 * </p>
 */
public class DateUtil {

    /**
     * 日期格式：yyyyMM
     */
    public static final String MonthsPattern = "yyyyMM";
    /**
     * 日期格式：yyyyMMdd
     */
    public static final String datePattern = "yyyyMMdd";
    /**
     * 日期时间格式：yyyyMMddHHmmss
     */
    public static final String fullPattern = "yyyyMMddHHmmss";
    /**
     * 日期时间格式：yyyyMMddHHmmss
     */
    public static final String readPattern = "yyyy-MM-dd HH:mm:ss,SSS";

    /**
     * 日期时间格式：yyyy-MM-dd
     */
    public static final String dateShowFormat = "yyyy-MM-dd";

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String standardPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    /**
     * 获取当前时间 格式： yyyyMMddHHmmss
     *
     * @return 字符日期 格式：yyyyMMddHHmmss
     */
    public static String getCurrent() {
        return getCurrent(fullPattern);
    }

    /**
     * 获取当前时间 格式： 自定义
     *
     * @param pattern 时间格式
     * @return 自定义格式的当前时间
     */
    public static String getCurrent(String pattern) {
        return DateTime.now().toString(pattern);
    }

    /**
     * 将字符串转换成固定格式时间
     *
     * @param date    日期
     * @param pattern 自定义格式
     * @return 转换后日期
     */
    public static Date parse(String date, String pattern) {
        DateTime dateTime = parseTime(date, pattern);
        if (dateTime == null) return null;
        return dateTime.toDate();
    }

    /**
     * 将字符串转换成固定格式时间
     *
     * @param date    日期
     * @param pattern 自定义格式
     * @return 转换后日期
     */
    public static DateTime parseTime(String date, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(date);
    }

    /**
     * Date类型转换String 格式：yyyyMMdd
     *
     * @param date 日期
     * @return String类型
     */
    public static String defaultFormat(Date date) {
        if (date == null) return null;
        return new DateTime(date).toString(datePattern);
    }

    /**
     * Date类型转换String 格式：自定义
     *
     * @param date    日期
     * @param pattern 自定义格式
     * @return String类型
     */
    public static String format(Date date, String pattern) {
        if (date == null) return null;
        return new DateTime(date).toString(pattern);
    }

    /**
     * 获取当前时间
     *
     * @return Date
     */
    public static Date getCurrentDate(String pattern) {
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return DateTime.parse(new DateTime().toString(pattern), format).toDate();
    }

    /**
     * 根据 pattern 将 dateTime 时间进行格式化
     * 用来去除时分秒，具体根据结果以 pattern 为准
     *
     * @param date payDate 时间
     * @return payDate 时间
     */
    public static Date formatToDate(Date date, String pattern) {
        if (date == null) return null;
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return DateTime.parse(new DateTime(date).toString(pattern), format).toDate();
    }

    /**
     * 日期增减，负数为减
     *
     * @param dayNum 天数
     * @return 时间
     */
    public static Date plusDays(int dayNum) {
        return new DateTime().plusDays(dayNum).toDate();
    }

    /**
     * 日期增减，负数为减
     *
     * @param dayNum 天数
     * @return 时间
     */
    public static Date plusDays(Date date, int dayNum) {
        return new DateTime(date).plusDays(dayNum).toDate();
    }

    /**
     * 分钟增减，负数为减
     *
     * @param minutes 分钟数
     * @return 时间
     */
    public static Date plusMinutes(Date date, int minutes) {
        return new DateTime(date).plusMinutes(minutes).toDate();
    }

    /**
     * 月份增减，负数为减
     *
     * @param monthsNum 天数
     * @return 时间
     */
    public static Date plusMonths(Date date, int monthsNum) {
        return new DateTime(date).plusMonths(monthsNum).toDate();
    }

    /**
     * 增加时间
     *
     * @param date          时间
     * @param calendarField 时间格式
     * @param amount        间隔
     * @return 日期
     */
    public static Date addDate(Date date, int calendarField, int amount) {

        if (date == null) {
            throw new IllegalArgumentException("The date could not be null!");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);

        return c.getTime();
    }

    /**
     * 按秒偏移,根据{@code source}得到{@code seconds}秒之后的日期<Br>
     *
     * @param source  , 要求非空
     * @param seconds , 秒数,可以为负
     * @return 新创建的Date对象
     */
    public static Date addSeconds(Date source, int seconds) {
        return addDate(source, Calendar.SECOND, seconds);
    }

    /**
     * 根据传入的时分秒毫秒获取固定的当日时间点
     *
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param millisecond 毫秒
     * @return 时间点
     */
    public static Calendar getCurrentCalendar(int hour, int minute, int second, int millisecond) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar;
    }

    /**
     * 根据传入的时分秒毫秒获取指定日期的时间点
     *
     * @param date        日期
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param millisecond 毫秒
     * @return 时间点
     */
    public static Calendar getDateCalendar(Date date, int hour, int minute, int second, int millisecond) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar;
    }

    /**
     * 计算当月的最后一天
     *
     * @param date 日期
     * @return 当月最后一天
     */
    public static String getMonthEndDay(Date date) {
        DateTime dateTime = getDateTime(date);
        return String.valueOf(getDateTime(getMonthEnd(dateTime)).dayOfMonth().get());
    }

    /**
     * 计算当月的第一天
     *
     * @param date 日期
     * @return 当月最后一天
     */
    public static Date getMonthBegDay(Date date) {
        DateTime dateTime = getDateTime(date);
        return dateTime.plusDays(1 - dateTime.getDayOfMonth()).toDate();
    }

    /**
     * 计算当月的最后一天
     *
     * @param dateTime 日期
     * @return 日期对象
     */
    public static Date getMonthEnd(DateTime dateTime) {
        return dateTime.plusMonths(1).plusDays(-dateTime.plusMonths(1).getDayOfMonth()).toDate();
    }

    /**
     * 判断是否是每月的第一天
     *
     * @param dateTime 日期
     * @return 日期对象
     */
    public static boolean checkMonthFirstDate(DateTime dateTime) {
        return 1 == dateTime.getDayOfMonth();
    }

    /**
     * 获取DateTime日期对象
     *
     * @param date 日期
     * @return DateTime对象
     */
    public static DateTime getDateTime(Date date) {
        return DateUtil.parseTime(DateUtil.format(date, DateUtil.datePattern), DateUtil.datePattern);
    }

    /**
     * 返回当月最后一天的日期
     *
     * @param date 日期
     * @return 返回当月最后一天的日期
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param date1 时间参数 1
     * @param date2 时间参数 2
     * @return 相差天数
     */
    public static long getDistanceDays(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        return diff / (1000 * 60 * 60 * 24);
    }

    /**
     * 两个时间间的差值
     *
     * @param date1 时间参数 1
     * @param date2 时间参数 2
     * @return 相差时间
     */
    public static long getTimeDifference(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();

        return time1 - time2;
    }

    /**
     * 获取当前是一周中的第几天
     *
     * @param date yyyyMMdd 或者 yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String getDayOfWeek(String date) throws ParseException {
        date = date.replaceAll("[-]", "");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        cal.setTime(sdf.parse(date));
        int week = cal.get(Calendar.DAY_OF_WEEK);
        return String.valueOf((week == 1) ? 7 : week - 1);
    }

    /**
     * 获取本月的最后一天
     *
     * @param date yyyyMMdd 或者 yyyy-MM-dd
     * @return yyyyMMdd
     * @throws ParseException
     */
    public static String getLastDayOfMonth(String date) throws ParseException {
        date = date.replaceAll("[-]", "");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        cal.setTime(sdf.parse(date));
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取上个月的最后一天
     *
     * @param date yyyyMMdd 或者 yyyy-MM-dd
     * @return yyyyMMdd
     * @throws ParseException
     */
    public static String getLastDayOfLastMonth(String date) throws ParseException {
        date = date.replaceAll("[-]", "");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        cal.setTime(sdf.parse(date));
        cal.add(Calendar.MONTH, -1);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }

    /**date
     * 判断传入参数格式为yyyyMMdd的数据是否是有效的日期类型
     * @param inputDate
     * @return
     */
    public static boolean validDate(String inputDate) {
        return inputDate.matches("[1-9][0-9]{3}(0[0-9]|1[0-2])(0[0-9]|1[0-9]|2[0-9]|3[0-1])");
    }

    /**
     * 获取当前日距离年初(1月1日)的天数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getDayOfYear(String date) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(date));
        int days = c.get(Calendar.DAY_OF_YEAR);
        return days - 1;
    }

}