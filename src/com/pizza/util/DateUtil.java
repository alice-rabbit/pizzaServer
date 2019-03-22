package com.pizza.util;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.UUID;

public class DateUtil {

	public static String getCurDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String curdate = sdf.format(date);
		return curdate;
	}

	public static String getCurDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String curdate = sdf.format(date);
		return curdate;
	}

	public static String getCurTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		String curdate = sdf.format(date);
		return curdate;
	}

	public static long secondsBetween(String dateStart, String dateStop) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //毫秒ms
            long diff = d2.getTime() - d1.getTime();

          
            long diffSeconds = diff / (1000);
           
            return diffSeconds;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
	}
}
