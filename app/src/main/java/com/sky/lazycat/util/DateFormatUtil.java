package com.sky.lazycat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lizhaotailang on 2017/5/21.
 *
 * A util class for formatting the date between string and long.
 */

public final class DateFormatUtil {

    private DateFormatUtil() {
        throw new AssertionError("No construction for constant class");
    }

    public static String formatZhihuDailyDateLongToString(long date) {
        String sDate;
        Date d = new Date(date + 24*60*60*1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);

        return sDate;
    }

    public static long formatZhihuDailyDateStringToLong(String date) {
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyyMMdd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d == null ? 0 : d.getTime();
    }

    public static String formatDoubanMomentDateLongToString(long date){
        String sDate;
        Date d = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        sDate = format.format(d);

        return sDate;
    }


    public static boolean isTheSameDay(String dataOne,String dateOther){
        Date one = null;
        Date anOther = null;

        try {
            one = new SimpleDateFormat("yyyy-MM-dd").parse(dataOne.substring(0,10));
            anOther = new SimpleDateFormat("yyyy-MM-dd").parse(dateOther.substring(0,10));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar _one = Calendar.getInstance();
        _one.setTime(one);
        Calendar _anOther = Calendar.getInstance();
        _anOther.setTime(anOther);
        int oneDay = _one.get(Calendar.DAY_OF_YEAR);
        int anotherDay = _anOther.get(Calendar.DAY_OF_YEAR);

        return oneDay == anotherDay;
    }

    public static String formatDateStringToString(String date){
        return date.substring(0,10).replace('-','/');
    }

    public static String formatZhihuDailyDateToString(long date) {
        Date d = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(d);
    }

}
