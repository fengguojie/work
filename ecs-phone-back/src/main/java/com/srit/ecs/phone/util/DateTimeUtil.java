package com.srit.ecs.phone.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateTimeUtil {

	public static String FORMAT_DEFAULT_MIN = "yyyyMMddHHmmss";
	public static String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
	public static String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static String FORMAT_DEFAULT_CH = "yyyy年MM月dd日 HH时mm分ss秒";
	public static String FORMAT_DEFAULT_YMD = "yyyy-MM-dd";
	public static String FORMAT_DEFAULT_YMDHM ="yyyy-MM-dd HH:mm";
	public static String FORMAT_DEFAULT_M = "yyyy年MM月";
	public static String FORMAT_DEFAULT_ME = "yyyy-MM";
	public static String FORMAT_YMD = "yyyyMMdd";

	private static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(FORMAT_DEFAULT_MIN);
	private static final SimpleDateFormat yyyy_MM_DDHHmm = new SimpleDateFormat(FORMAT_DEFAULT_YMDHM);
	private static final SimpleDateFormat yyyy_MM_DDHHmmss = new SimpleDateFormat(FORMAT_DEFAULT);
	private static ThreadLocal<Date> local = new ThreadLocal<>();
	
	public static void setLocalDate(Date date) {
		local.set(date);
		
	}
	public static void removeLocalDate() {
		local.remove();
	}
	public static Date getLocalDate() {
		Date date = local.get();
		return date == null ? Calendar.getInstance().getTime() : date;
	}
	public static Date current() {
		return new Date();
	}

	public static Date addDate(Date date, int day) {
		return add(date,Calendar.DATE,day);
	}

	public static Integer[] getYMDHMS(Date date) {
		Calendar cal = getCalendar(date);
		return new Integer[] { cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
				cal.get(Calendar.SECOND) };
	}

	public static Integer getWeekOfMonth(Date date) {
		Calendar cal = getCalendar(date);

		return cal.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 取本周周一的日期(周一为一周第一天)
	 * 
	 * @param date
	 * @return
	 */
	public static Date thisWeekMonday(Date date) {
		Calendar cal = getCalendar(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
	/**
	 *把日期转换为 yyyy-MM-dd HH:mm:ss格式的日期字符.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String dateFormatLong(Date date) {
		return dateFormat(date, YYYY_MM_DD_HH_MI_SS);
	}

	/**
	 * 日期转换为制定的字符串格式
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateFormat(Date date, String format) {
		if (null == date)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 返回星期数（周日返回7）
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getDayOfWeek(Date date) {
		Calendar cal = getCalendar(date);
		int re = cal.get(Calendar.DAY_OF_WEEK);
		if (re == 0) {
			return 7;
		} else {
			return re - 1;
		}
	}
	public static Date getSixHoursLater(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, +6);
		return cal.getTime();

	}
	public static Date addSecond(Date date, int num) {
		return add(date, Calendar.SECOND, num);
	}

	public static Date addMinute(Date date, int num) {
		return add(date, Calendar.MINUTE, num);
	}

	public static Date addHour(Date date, int num) {
		return add(date, Calendar.HOUR_OF_DAY, num);
	}

	public static Date addDay(Date date, int num) {
		return add(date, Calendar.DAY_OF_MONTH, num);
	}

	public static Date add(Date date, int field, int num) {
		Calendar cal = getCalendar(date);
		cal.add(field, num);
		return cal.getTime();
	}

	public static Date firstOfMonth(Date date) {
		return first(date, Calendar.DAY_OF_MONTH);
	}

	public static Date lastOfMonth(Date date) {
		return last(date, Calendar.DAY_OF_MONTH);
	}

	public static Date firstOfHour(Date date) {
		return first(date, Calendar.HOUR_OF_DAY);
	}

	public static Date lastOfHour(Date date) {
		return last(date, Calendar.HOUR_OF_DAY);
	}

	public static Date first(Date date, int d) {
		if (null == date)
			return date;
		Calendar cal = getCalendar(date);
		cal.set(d, cal.getMinimum(d));
		return cal.getTime();
	}

	public static Date last(Date date, int d) {
		if (null == date)
			return date;
		Calendar cal = getCalendar(date);
		cal.set(d, cal.getMaximum(d));
		return cal.getTime();
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		return cal;
	}
	/**
	 * 当前天前一天的起始时间
	 */
	public static Date getYesterdayStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.addDay(new Date(), -1));

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 当前天前一天的结束时间
	 */
	public static Date getYesterdayEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 当前天前一天的指定时间
	 */
	public static Date getYesterdayTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.addDay(new Date(), -1));

		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}
	public static String dateToStrOfYmdFormat(Date date) {
		return dateToStr(date, FORMAT_DEFAULT_YMD);
	}

	public static String dateToStrOfDefaulfFormat(Date date) {
		return dateToStr(date, FORMAT_DEFAULT);
	}

	/**
	 * 日期转换为制定的字符串格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		if (null == date)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date strToDateOfYmdFormat(String dateStr) {
		return strToDate(dateStr, FORMAT_DEFAULT_YMD);
	}

	public static Date strToDateOfDefaulfFormat(String dateStr) {
		return strToDate(dateStr, FORMAT_DEFAULT);
	}

	/**
	 * 字符串转换为日期类型
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date strToDate(String dateStr, String format) {
		if (null == dateStr || dateStr.isEmpty())
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算两日期间相差天数.
	 * 
	 * 
	 * @param d1
	 *            开始日期 日期型
	 * @param d2
	 *            结束日期 日期型
	 * @return long 天数
	 */
	public static long signDaysBetweenTowDate(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) / (3600 * 24 * 1000);
	}

	
	/**
	 * 获取2个日期("yyyy-MM-dd")之间的所有日期
	 * 
	 * @param d1
	 * @param d2
	 * @return GregorianCalendar[]
	 * @throws ParseException
	 */
	public static Calendar[] getBetweenDate(String d1, String d2) {
		List<GregorianCalendar> v = new ArrayList<GregorianCalendar>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar gc1 = new GregorianCalendar(), gc2 = new GregorianCalendar();
		try {
			gc1.setTime(sdf.parse(d1));
			gc2.setTime(sdf.parse(d2));
			do {
				GregorianCalendar gc3 = (GregorianCalendar) gc1.clone();
				v.add(gc3);
				gc1.add(Calendar.DAY_OF_MONTH, 1);
			} while (!gc1.after(gc2));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return v.toArray(new GregorianCalendar[v.size()]);
	}

	/**
	 * 获取2个日期("yyyy-MM-dd")之间的所有日期
	 *
	 * @param d1
	 * @param d2
	 * @return GregorianCalendar[]
	 * @throws ParseException
	 */
	public static Calendar[] getBetweenDate(Date d1, Date d2) {
		List<GregorianCalendar> v = new ArrayList<GregorianCalendar>();
		Calendar gc1 = new GregorianCalendar(), gc2 = new GregorianCalendar();
		gc1.setTime(d1);
		gc2.setTime(d2);
		do {
			GregorianCalendar gc3 = (GregorianCalendar) gc1.clone();
			v.add(gc3);
			gc1.add(Calendar.DAY_OF_MONTH, 1);
		} while (!gc1.after(gc2));

		return v.toArray(new GregorianCalendar[v.size()]);
	}
	

	/**
	 * 返回45天前时间
	 * @param date
	 * @return 
	 */
	public static Date getOneMonthBefore(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -45);
		return cal.getTime();
		
	}
	
	/**
	 * 返回1天后时间
	 * @param date
	 * @return 
	 */
	public static Date getOneDayLater(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +1);
		return cal.getTime();
		
	}
	
	/**
	 * 格式化
	 * @return
	 */
	public static Date format(String date,String ... formats){
		for(String f : formats){
			Date d = strToDate(date,f);
			if(d == null){
				continue;
			}else {
				return d;
			}
		}
		return null;
	}
	
	/**
	 * 处理用户端、客服端时间格式问题
	 * @param date
	 * @return
	 */
	public static Date format(String date){
		return format(date,new String[]{
				FORMAT_DEFAULT,
				FORMAT_DEFAULT_YMDHM,
				FORMAT_DEFAULT_YMD,
				FORMAT_DEFAULT_ME,
				FORMAT_DEFAULT_M
		});
	}
	
	/**
     * 当前天的起始时间
     */
    public static Date getDayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        return calendar.getTime();
    }

    /**
     * 当前天的结束时间
     */
    public static Date getDayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateTimeUtil.addDay(new Date(), 1));
        
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        return calendar.getTime();
    }

	/**
	 * 当起始时间
	 */
	public static Date getDayStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}


	public static Date getDayEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.addDay(date, 1));

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

    /**
     * 当前月的开始时间
     */
    public static Date getMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        return calendar.getTime();
    }

    /**
     *  在日期上添加月的日期。
     *
     * @param date the date
     * @param month the month
     * @return the date
     */
    public static Date addMonth(Date date,int month) {
        return add(date,Calendar.MONTH,month);
    }
    
    /**
     * 当前月的结束时间
     */
    public static Date getMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateTimeUtil.addMonth(new Date(), 1));
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        return calendar.getTime();
    }
	
	/**
	 * 处理用户端 客服端时间字段问题
	 * @param date
	 * @return
	 */
	public static String parseDate(String date,String msg){
		Date d = format(date);
		if(d == null){
			throw new RuntimeException(msg);
		}
		return dateToStrOfDefaulfFormat(d);
	}
	
	public static String formatTimeToDateStr(String time) {
		try {
			return yyyy_MM_DDHHmmss.format(yyyyMMddHHmmss.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatTimeToDate(Date date) {
		return yyyyMMddHHmmss.format(date);
	}
	
	public static String formatDateToStr(Date date) {
		return yyyy_MM_DDHHmmss.format(date);
	}
	
	public static String formatDate(Date date) {
		return yyyy_MM_DDHHmm.format(date);
	}
	
	/**
	 * 判断两个时间是否超过半个小时
	 * @return
	 */
	public static boolean timeout30Min(Date oldDate, Date newDate) {
		if(newDate.getTime() - oldDate.getTime() > 1000 * 60 * 30) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 返回日期为类似于20171226的整数形式
	 * @return
	 * @throws ParseException
	 */
	public static long getYesterday() throws ParseException {
		Calendar d = Calendar.getInstance();
		d.add(Calendar.DATE, -1);
		long dateLong = d.get(Calendar.YEAR)*10000 + (d.get(Calendar.MONTH)+1)*100 + d.get(Calendar.DATE);
		return dateLong;
	}
	
	public static void main(String[] args) {
		Date ymd = format("2015-08-14");
		Date ymdhm = format("2015-08-14 00:00");
		Date defaultt = format("2015-09-01 00:00:00");
		Date md = format("2015年9月");
		System.out.println(ymd);
		System.out.println(ymdhm);
		System.out.println(defaultt);
		System.out.println(md);
		System.out.println(dateToStrOfDefaulfFormat(ymd));
		System.out.println(dateToStrOfDefaulfFormat(ymdhm));
		System.out.println(dateToStrOfDefaulfFormat(defaultt));
		System.out.println(dateToStrOfDefaulfFormat(md));
		Date old = format("2017-06-24 15:00:20");
		System.out.println(timeout30Min(old, new Date()));
	}
}