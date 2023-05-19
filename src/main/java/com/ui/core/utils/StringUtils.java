package com.ui.core.utils;


import java.util.Calendar;
import java.util.Date;

import static com.ui.core.utils.DateUtils.*;


public class StringUtils {

    public static String getRandomNumber() {
        String datePattern = DAY + MONTH_NUMBER_FORM + YEAR_SHORT_FORM;
        return getDateTime(EET_TIME_ZONE, datePattern, getCalendarDateTime()) + (int) (Math.random() * 99999);
    }

    private static Calendar getCalendarDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 0);
        return calendar;
    }
}




