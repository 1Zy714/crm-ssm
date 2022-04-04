package com.bjpowernode.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对Date类型数据进行处理的工具类
 * */
public class DateUtils {
    /**
     * 对Date进行yyyy-MM-dd HH:mm:ss
     * */
    public static String formatDateTime(Date date){
        String now;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        now = sdf.format(date);
        return now;
    }
    /**
     * 对Date进行yyyy-MM-dd
     * */
    public static String formatDate(Date date){
        String now;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        now = sdf.format(date);
        return now;
    }
    /**
     * 对Date进行HH:mm:ss
     * */
    public static String formatTime(Date date){
        String now;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        now = sdf.format(date);
        return now;
    }
}
