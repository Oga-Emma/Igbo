package com.example.seven.igbocalender.smallWidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.seven.igbocalender.bigWidget.BigClockWidgetBroadcast;

import java.util.Calendar;

/**
 * Created by seven on 1/18/18.
 */

public class SmallWidgetCLockAlarmManger {

    public static void createAlarmManager(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);

        if (alarmManager != null) {
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//                alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(),
//                        createClockTickIntent(context));
//            }else {
//                alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),
//                        createClockTickIntent(context));
//            }

            alarmManager.setRepeating(AlarmManager.RTC,
                    calendar.getTimeInMillis(),
                    10000,
                    createClockTickIntent(context));
        }
    }

    public static void cancelAlarmManager(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(createClockTickIntent(context));
        }
    }

    private static PendingIntent createClockTickIntent(Context context) {
        Intent intent = new Intent(context, BigClockWidgetBroadcast.class);
//        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 200, intent,
                        PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }
}
