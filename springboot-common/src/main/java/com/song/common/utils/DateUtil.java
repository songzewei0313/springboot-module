package com.song.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author song
 */
public class DateUtil {
    private static SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");

    private static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

    private static SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前年度
     * @return 2020
     */
    public static String getYear(){
        return yyyy.format(new Date());
    }

    /**
     * 获取当前时间
     * @return 2020-08-14
     */
    public static String yyyy_MM_dd(){
        return yyyy_MM_dd.format(new Date());
    }

    /**
     * 获取当前时间
     * @return 20200814
     */
    public static String yyyyMMdd(){
        return yyyyMMdd.format(new Date());
    }

    /**
     * 获取当前时间
     * @return 2020-08-14 22:10:18
     */
    public static String yyyy_MM_dd_HH_mm_ss(){
        return yyyy_MM_dd_HH_mm_ss.format(new Date());
    }

    /**
     * 计算某个时间+XX分钟后的时间
     * @param date 时间
     * @param minute 相加的分钟
     * @return
     * @throws Exception
     */
    public static String dataPlusMin(String date, int minute)throws  Exception{
        Date dateStr=yyyy_MM_dd_HH_mm_ss.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStr);
        calendar.add(Calendar.MINUTE, minute);
        return yyyy_MM_dd_HH_mm_ss.format(calendar.getTime());
    }

    /**
     * 比较2个时间的大小
     * @param dataType 时间格式:"yyyy-MM-dd"，"yyyy-MM-dd HH:mm:ss"
     * @param date1 时间1
     * @param date2 时间2
     * @return 1：时间1<时间2，2：时间1>时间2，3：时间1=时间2
     * @throws ParseException
     */
    public static int compareDate(String dataType,String date1,String date2) throws ParseException {
        int flag = 0;
        SimpleDateFormat sdf=new SimpleDateFormat(dataType);
        Date bt=sdf.parse(date1);
        Date et=sdf.parse(date2);
        if (bt.before(et)){
            flag = 1;
        }else if(bt.equals(et)){
            flag = 3;
        }else{
            flag = 2;
        }
        return flag;
    }
    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
