package com.example.himanshujain.taranpanth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


    public static int getDaysDifference(String date) {
        int numOfDays = 0;

        try {
            Date userDob = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Date today = new Date();
            long diff = today.getTime() - userDob.getTime();
            int numOfYear = (int) ((diff / (1000 * 60 * 60 * 24)) / 365);
            numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
            int hours = (int) (diff / (1000 * 60 * 60));
            int minutes = (int) (diff / (1000 * 60));
            int seconds = (int) (diff / (1000));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfDays;
    }

}
