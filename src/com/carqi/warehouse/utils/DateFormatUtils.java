package com.carqi.warehouse.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * 
 *  Class Name: DateFormatUtils.java
 *  时间util
 *  @author Yu Liu  DateTime 2013-10-26 上午9:24:28    
 *  @version 1.0
 *  @company 长春优狐科技开发有限公司
 */

@SuppressLint("SimpleDateFormat")
public class DateFormatUtils {
	private static final String FORMAT_DATE_YYYY_MM_DD = "yyyy-MM-dd";
	private static final String FORMAT_DATE_YYYY_MM_DD2 = "yyyy/MM/dd";
	private static final String FORMAT_DATE_AND_TIME_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	@SuppressLint("SimpleDateFormat")
	/**时间戳转化Sting 类型date*/
	public static String formatDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);

		String newKey;
		if (StringUtils.isEmpty(date) || date.equals("0")) {
			return "";
		} else {
			newKey = date;
		}
		Date dt = new Date(Long.parseLong(newKey));
		String sDateTime = null;

		sDateTime = sdf.format(dt);
		return sDateTime;
	}
	
	public static String formatDate1(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);

		String newKey;
		if (StringUtils.isEmpty(date)) {
			newKey = "";
		} else {
			newKey = date;
		}
		Date dt = new Date(Long.parseLong(newKey));
		String sDateTime = null;

		sDateTime = sdf.format(dt);
		return sDateTime;
	}
	
	public static String formatDate(long millis) {
		if (millis == 0) 
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD2);
		Date dt = new Date(millis);
		String sDateTime = null;

		sDateTime = sdf.format(dt);
		return sDateTime;
	}
	
	public static String formatDateAndTime(long millis) {
		if (millis == 0) 
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_AND_TIME_YYYY_MM_DD_HH_MM);
		Date dt = new Date(millis);
		String sDateTime = null;

		sDateTime = sdf.format(dt);
		return sDateTime;
	}
	public static Date parseDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_AND_TIME_YYYY_MM_DD_HH_MM);
		try {
			return sdf.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Long parseDateToLong(String dateString) {
		if (StringUtils.isEmpty(dateString) || dateString.equals("0")) {
			return 0L;
		}
		Date date = parseDate(dateString);
		if (date == null) {
			return null;
		} else {
			return date.getTime();
		}
	}
	
	public static String convertNotNull(String dateString) {
		if (StringUtils.isEmpty(dateString) || dateString.equals("0")) {
			return "null";
		}
		Date date = parseDate(dateString);
		if (date == null) {
			return "null";
		} else {
			return String.valueOf(date.getTime());
		}
	}

	public static String convertNotNull(Long long1) {
		String str = String.valueOf(long1);
		if (StringUtils.isEmpty(str) || long1.equals("0")) {
			return "null";
		}
		Date date = parseDate(str);
		if (date == null) {
			return "null";
		} else {
			return String.valueOf(date.getTime());
		}
	}
	public static String parseDateToLongString(String dateString) {
		if (StringUtils.isEmpty(dateString)) {
			return "";
		}
		Long dateLong = parseDateToLong(dateString);
		if (dateLong == null) {
			return null;
		} else {
			return String.valueOf(dateLong);
		}
	}
	
	public static long parseDateToLong2(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
		Date date;
		try {
			date = sdf.parse(dateString);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String checkNull(String birthday) {
		if (StringUtils.isEmpty(birthday) || birthday.equals("0"))
			return "null";
		return birthday;
	}

	/** 从身份证中取出生日信�?*/
	public static String getBirthFromId(String id) {
		if (StringUtils.isEmpty(id) || id.length() < 15) {
			return "";
		}
		String year = id.substring(6, 10);
		String month = id.substring(10, 12);
		String day = id.substring(12, 14);
		return parseDateToLongString(year + "-" + month + "-" + day);
	}
	public static long getSystemDate(){
	    Date date = new Date();
        return date.getTime();
	    
	}
	
	public static String formatSystemDate(){
	    long millis = getSystemDate();
        return formatDate(millis);
	}
	
	// 获得日期型系统日期
    public static long systemDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_AND_TIME_YYYY_MM_DD_HH_MM);
        String strDate = sdf.format(date);
        return parseDateToLong(strDate);
    }
    
    public static long getSystemDate2(){
    	Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
        String strDate = sdf.format(date);
        return parseDateToLong(strDate);
    }
    
    public static long getTodayStartTime(){
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
		String strDate = sdf.format(date);
		Date date2 = null;
		try {
			date2 = sdf.parse(strDate);
			SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT_DATE_AND_TIME_YYYY_MM_DD_HH_MM);
			String strDate2 = sdf2.format(date2);
			Date date3 = sdf2.parse(strDate2);
			return date3.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    	
    }
    public static long getTodayEndTime(){
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
		String strDate = sdf.format(date);
		Date date2 = null;
		try {
			date2 = sdf.parse(strDate);
			SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT_DATE_AND_TIME_YYYY_MM_DD_HH_MM);
			String strDate2 = sdf2.format(date2);
			Date date3 = sdf2.parse(strDate2);
			return date3.getTime() + 86399000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }
	
}
