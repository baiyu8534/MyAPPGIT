package com.example.administrator.myappgit.utils;

import java.util.Calendar;

public class DateUtil {
    private static final String DATE_SEPARATOR = "-";
    private static final String MIDDLE_SEPARATOR = " ";
    private static final String TIME_SEPARATOR = ":";

    public static String getCurrentDateString() {
        int[] date = getCurrentDateArray();
        String dateS = null;
        dateS = getDateString(date[0], date[1], date[2], date[3], date[4], date[5]);
        return dateS;

    }

    public static String getDateString(int year, int month, int day, int hour, int minute, int second) {
        String monthS = month < 10 ? "0" + String.valueOf(month) : String.valueOf(month);
        String dayS = day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
        String hourS = hour < 10 ? "0" + String.valueOf(hour) : String.valueOf(hour);

        if (hour != -1 && minute != -1 && second != -1) {
            String minuteS = minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute);
            String secondS = second < 10 ? "0" + String.valueOf(second) : String.valueOf(second);

            return year + DATE_SEPARATOR + monthS + DATE_SEPARATOR + dayS + MIDDLE_SEPARATOR + hourS + TIME_SEPARATOR + minuteS
                    + TIME_SEPARATOR + secondS;
        }

        return year + DATE_SEPARATOR + monthS + DATE_SEPARATOR + dayS;
    }

    /**
     * get current date information integer format
     *
     * @return int[0] - year, int[1] - month, int[2] - day
     * int[3] - hour, int[4] - minute, int[5] - second
     */
    public static int[] getCurrentDateArray() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return (new int[]{year, month, day, hour, minute, second});
    }

    /**
     * nextDay 获取下一天的日期
     */
    public static String nextDayString() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        day += 1;
        if (day > DaysOfMonth(year, month)) {
            day = 1;
            int[] temp = nextMonth(year, month);
            year = temp[0];
            month = temp[1];
        }

        return getDateString(year, month, day, hour, minute, second);
    }

    /**
     * DaysOfMonth 获取某年某月有多少天
     */
    public static int DaysOfMonth(int yyyy, int mm) {
        if (mm < 1) {
            mm = 12;
            yyyy = yyyy - 1;
        }

        if (mm > 12) {
            mm = 1;
            yyyy = yyyy + 1;
        }

        switch (mm) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((yyyy % 4 == 0) && (yyyy % 100 != 0)) || (yyyy % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
        }
        return 0;
    }

    /**
     * nextMonth获取下个月的月份
     */
    public static int[] nextMonth(int yyyy, int mm) {
        if (mm == 12) {
            yyyy += 1;
            mm = 1;
        } else {
            mm += 1;
        }

        return new int[]{yyyy, mm};
    }
}
