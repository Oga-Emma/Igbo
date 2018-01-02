package com.example.seven.igbocalender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by seven on 1/1/18.
 */

public class Utils {
    public static final DateFormat getDateFormat(){
        return new SimpleDateFormat("EE, dd MMMM yyyy");
    }

}
