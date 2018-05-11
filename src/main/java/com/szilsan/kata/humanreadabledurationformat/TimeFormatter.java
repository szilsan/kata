package com.szilsan.kata.humanreadabledurationformat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeFormatter {

    private enum DURATION {
        now,
        second,
        seconds,
        minute,
        minutes,
        hour,
        hours,
        day,
        days,
        year,
        years
    }

    private static final long SEC_IN_MIN = 60;
    private static final long SEC_IN_HOUR = SEC_IN_MIN * 60;
    private static final long SEC_IN_DAY = SEC_IN_HOUR * 24;
    private static final long SEC_IN_YEAR = SEC_IN_DAY * 365;

    public static String formatDuration(int seconds) {
        if (seconds == 0) {
            return DURATION.now + "";
        }

        List<String> ret = new ArrayList<String>();
        int tempValue = seconds;

        long years = tempValue / SEC_IN_YEAR;
        if (years > 0) {
            tempValue -= years * SEC_IN_YEAR;
            ret.add(years + (years == 1 ? " year" : " years"));
        }

        long days = TimeUnit.DAYS.convert(tempValue, TimeUnit.SECONDS);
        if (days > 0) {
            tempValue -= days * SEC_IN_DAY;
            ret.add(days + (days == 1 ? " day" : " days"));
        }

        long hours = TimeUnit.HOURS.convert(tempValue, TimeUnit.SECONDS);
        if (hours > 0) {
            tempValue -= hours * SEC_IN_HOUR;
            ret.add(hours + (hours == 1 ? " hour" : " hours"));
        }

        long mins = TimeUnit.MINUTES.convert(tempValue, TimeUnit.SECONDS);
        if (mins > 0) {
            tempValue -= mins * SEC_IN_MIN;
            ret.add(mins + (mins == 1 ? " minute" : " minutes"));
        }

        long sec = tempValue;
        if (sec > 0) {
            ret.add(sec + (sec == 1 ? " second" : " seconds"));
        }

        String retValue = "";
        for (int i = 0; i < ret.size(); i++) {
            if (i == ret.size() - 1) {
                retValue += ret.get(i);
            } else if (i == ret.size() - 2) {
                retValue += ret.get(i) + " and ";
            } else {
                retValue += ret.get(i) + ", ";
            }
        }

        return retValue;
    }
}