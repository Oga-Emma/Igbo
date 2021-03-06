package com.example.seven.igbocalender.bigWidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by seven on 1/27/18.
 */

public class BigClockAlarmManager {

    public static void startTimerAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);

        if (alarmManager != null) {

            alarmManager.setRepeating(AlarmManager.RTC,
                    calendar.getTimeInMillis(),
                    1000,
                    timerAlarmIntent(context));
        }
    }

    public static void cancelTimerAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(timerAlarmIntent(context));
        }
    }

    private static PendingIntent timerAlarmIntent(Context context) {
        Intent intent = new Intent(context, BigClockWidget.class);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 2100, intent,
                        PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }

}
