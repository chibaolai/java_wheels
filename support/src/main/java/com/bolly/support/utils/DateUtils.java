package com.bolly.support.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {

    /**
     * yyyyMMddHHmmss的格式
     */
    public static final String FMT_DATETIME_CMPRS = "yyyyMMddHHmmss";

    /**
     * yyyyMMdd的格式
     */
    public static final String FMT_DATE_CMPRS = "yyyyMMdd";
    /**
     * yyyy-MM-dd HH:mm:ss的格式
     */
    public static final String FMT_DATETIME_WHOLE = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd的格式
     */
    public static final String FMT_DATE_WHOLE = "yyyy-MM-dd";

    private DateUtils() {

    }

    /**
     * 给定日期的开始
     * 
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        if (date == null) {
            return null;
        }
        return new DateTime(date.getTime()).withTimeAtStartOfDay().toDate();
    }

    /**
     * 给定日期的结束
     * 
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        if (date == null) {
            return null;
        }
        return new DateTime(date.getTime()).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59)
                .withMillisOfSecond(999).toDate();
    }

    /**
     * 当天的开始
     * 
     * @return
     */
    public static Date currentDayStart() {
        return getDayStart(new Date());
    }

    /**
     * 当天的结束
     * 
     * @return
     */
    public static Date currentDayEnd() {
        return getDayEnd(new Date());
    }

    /**
     * 当前月的开始
     * 
     * @return
     */
    public static Date currentMonthStart() {
        return DateTime.now().withTimeAtStartOfDay().withDayOfMonth(1).toDate();
    }

    /**
     * 当前月的结束
     * 
     * @return
     */
    public static Date currentMonthEnd() {
        return DateTime.now().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999)
                .withDayOfMonth(1).plusMonths(1).minusDays(1).toDate();
    }
    
    /**
     * 当前年的开始
     * 
     * @return
     */
    public static Date currentYearStart() {
        return DateTime.now().withTimeAtStartOfDay().withDayOfYear(1).toDate();
    }
    
    /**
     * 当前年的结束
     * 
     * @return
     */
    public static Date currentYearEnd() {
        return DateTime.now().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999)
                .withMonthOfYear(12).withDayOfMonth(1).plusMonths(1).minusDays(1).toDate();
    }
    
    /**
     * 按给定格式解析日期
     * 
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parse(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        String thePattern = StringUtils.isBlank(pattern) ? FMT_DATETIME_CMPRS : pattern;
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr.trim(), thePattern);
        } catch (ParseException e) {
            throw new UnexpectedException(e);
        }
    }

    /**
     * 按默认格式解析日期
     * 
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseCmprs(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        if (dateStr.trim().length() == FMT_DATETIME_CMPRS.length()) {
            return parse(dateStr.trim(), FMT_DATETIME_CMPRS);
        } else if (dateStr.trim().length() == FMT_DATE_CMPRS.length()) {
            return parse(dateStr.trim(), FMT_DATE_CMPRS);
        }
        throw new IllegalArgumentException("日期格式不合法:" + dateStr);
    }

    /**
     * 按给定格式格式化日期
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            return DateFormatUtils.format(date, FMT_DATETIME_CMPRS);
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 按默认格式格式化日期(yyyyMMddHHmmss)
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, FMT_DATETIME_CMPRS);
    }

    /**
     * 获取一个初始的DateTime，表示的时间：1970-1-1 00:00:00.000
     * 
     * @return
     */
    public static Date defaultDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        return calendar.getTime();
    }

    /**
     * 指定时间N秒后的时间
     * 
     * @param days
     * @return
     */
    public static Date addSecond(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 指定时间N天后的时间
     * 
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 当前N天前的时间
     * 
     * @param days
     * @return
     */
    public static Date lastDays(int days) {
        Date date = new Date();
        return addDay(date, -days);
    }

    /**
     * 当前一周前的时间
     * 
     * @return
     */
    public static Date lastWeek() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 计算年龄
     * 
     * @param referDate
     *            基准日期
     * @param birthday
     *            出生日期
     * @return
     */
    public static String getAgeByBirthday(Date referDate, Date birthday) {
        if (birthday == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        if (referDate != null && !referDate.before(birthday)) {
            cal.setTime(referDate);
        }
        int yearRefer = cal.get(Calendar.YEAR);
        int monthRefer = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthRefer = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int year = yearRefer - yearBirth;
        int month = monthRefer - monthBirth;
        int day = dayOfMonthRefer - dayOfMonthBirth;

        if (day < 0) {
            if (month <= 0) {
                year--;
                month += 11;
            } else {
                month--;
            }
            day += cal.getMaximum(Calendar.DATE);
        } else {
            if (month < 0) {
                year--;
                month += 12;
            }
        }
        String age = null;
        // 大于三年仅显示X岁。
        if (year >= 3) {
            age = year + "岁";
            // 大于一年小于三年，显示X岁X个月
        } else if (year >= 1) {
            if (month <= 0) {
                age = year + "岁";
            } else {
                age = year + "岁" + month + "个月";
            }
            // 小于一年
        } else {
            // 小于一个月，显示X天
            if (month <= 0) {
                age = day + "天";
            } else if (day <= 0) {
                age = month + "个月";
            } else {
                age = month + "个月" + day + "天";
            }
        }
        return age;
    }

    /**
     * 根据出生日期算年龄
     * @param bornDate	出生日期
     * @return
     */
	public static Integer getAgeByBornDate(Date bornDate) {
		Calendar cal = Calendar.getInstance();
		Integer yearNow = cal.get(Calendar.YEAR);
		Integer monthNow = cal.get(Calendar.MONTH) + 1;
		Integer dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(bornDate);
		Integer yearBirth = cal.get(Calendar.YEAR);
		Integer monthBirth = cal.get(Calendar.MONTH) + 1;
		Integer dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		Integer age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}
    
}
