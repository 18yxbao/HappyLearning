package com.example.happylearning.Data;

import java.text.SimpleDateFormat;

public class TimeUtil {
    public static String getTime(){
        long currentTime = System.currentTimeMillis();
        String timeNow = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(currentTime);
        return timeNow;
    }
}
