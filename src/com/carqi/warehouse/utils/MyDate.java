package com.carqi.warehouse.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 *  Class Name: MyDate.java
 *  时间类，获取不同格式的时间
 *  @author Yu Liu  DateTime 2013-10-26 上午8:57:20    
 */
@SuppressLint("SimpleDateFormat")
public class MyDate {
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
