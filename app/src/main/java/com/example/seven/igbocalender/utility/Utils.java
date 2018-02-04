package com.example.seven.igbocalender.utility;

import com.example.seven.igbocalender.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by seven on 1/1/18.
 */

public class Utils {
    public static final DateFormat getDateFormat() {
        return new SimpleDateFormat("EEEE, dd MMMM yyyy");
    }

    public static final int getMoonImageResource(String day) {

        if (day.contains("10"))
            return R.drawable.moon_10;
        else if (day.contains("11"))
            return R.drawable.moon_11;
        else if (day.contains("12"))
            return R.drawable.moon_12;
        else if (day.contains("13"))
            return R.drawable.moon_13;
        else if (day.contains("14"))
            return R.drawable.moon_14;
        else if (day.contains("15"))
            return R.drawable.moon_15;
        else if (day.contains("16"))
            return R.drawable.moon_16;
        else if (day.contains("17"))
            return R.drawable.moon_17;
        else if (day.contains("18"))
            return R.drawable.moon_18;
        else if (day.contains("19"))
            return R.drawable.moon_19;
        else if (day.contains("20"))
            return R.drawable.moon_20;
        else if (day.contains("21"))
            return R.drawable.moon_21;
        else if (day.contains("22"))
            return R.drawable.moon_22;
        else if (day.contains("23"))
            return R.drawable.moon_23;
        else if (day.contains("24"))
            return R.drawable.moon_24;
        else if (day.contains("25"))
            return R.drawable.moon_25;
        else if (day.contains("26"))
            return R.drawable.moon_26;
        else if (day.contains("27"))
            return R.drawable.moon_27;
        else if (day.contains("28"))
            return R.drawable.moon_28;
        else if (day.contains("29"))
            return R.drawable.moon_29;
        else if (day.contains("30"))
            return R.drawable.moon_30;
        else if (day.contains("1"))
            return R.drawable.moon_1;
        else if (day.contains("2"))
            return R.drawable.moon_2;
        else if (day.contains("3"))
            return R.drawable.moon_3;
        else if (day.contains("4"))
            return R.drawable.moon_4;
        else if (day.contains("5"))
            return R.drawable.moon_5;
        else if (day.contains("6"))
            return R.drawable.moon_6;
        else if (day.contains("7"))
            return R.drawable.moon_7;
        else if (day.contains("8"))
            return R.drawable.moon_8;
        else if (day.contains("9"))
            return R.drawable.moon_9;
        else
            return R.drawable.moon_16;
    }

}
