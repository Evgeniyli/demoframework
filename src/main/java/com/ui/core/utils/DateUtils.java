package com.ui.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static final String YEAR = "yyyy";
    public static final String MONTH_NUMBER_FORM = "MM";
    public static final String MONTH = "MM";
    public static final String MONTH_FULL = "MMMM";
    public static final String DAY = "dd";
    public static final String HOUR_24_FORMAT = "HH";
    public static final String MINUTE = "mm";
    public static final String SECOND = "ss";
    public static final String AM_PM_MARKER = "a";
    public static final String YEAR_SHORT_FORM = "yy";
    public static final String HOUR_12_FORMAT = "hh";
    public static final String EET_TIME_ZONE = "Europe/Kiev";

    /**
     * Get date and/or time in specified format and timezone
     *
     * @param timezone timezone
     * @param format   format
     * @return date and/or time in specified format
     */
    public static String getDateTime(String timezone, String format, Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Get date and/or time in specified format and timezone
     *
     * @param timezone timezone
     * @param format   date and/or time format
     * @param offset   offset (used when need future or past date and/or time)
     * @return date and/or time in specified format
     */
    public static String getDateTime(String timezone, String format, int offset) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return dateFormat.format(calendar.getTime());
    }


    /**
     * Get current timezone
     *
     * @return default timezone
     */
    public synchronized static String getCurrentTimeZone() {
        return TimeZone.getDefault().getID();
    }
}
