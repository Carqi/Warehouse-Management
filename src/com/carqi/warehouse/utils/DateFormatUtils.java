package com.carqi.warehouse.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * 时间工具类
 * @author Administrator
 * 2014-2-6 下午7:12:20
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
	/**
	 * 将日期格式转化为时间戳形式
	 * @author Administrator
	 * 2014-2-6 下午7:14:20
	 * @param dateString
	 * @return
	 */
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
	/**
	 * 将YYYY_MM_DD时间格式转换成时间戳格式
	 * @author Administrator
	 * 2014-2-6 下午7:26:41
	 * @param dateString
	 * @return
	 */
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
    
    public static String getFileName() {  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String date = format.format(new Date(System.currentTimeMillis()));  
        return date; 
    }  
  
    public static String getDateEN() {  
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String date1 = format1.format(new Date(System.currentTimeMillis()));  
        return date1;  
    }  
	
}
