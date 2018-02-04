package com.example.seven.igbocalender.bigWidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by seven on 1/18/18.
 */

public class BigCloclStartCLockAlarmManger {

    public static void createAlarmToStartTimerAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);

        if (alarmManager != null) {

            alarmManager.setRepeating(AlarmManager.RTC,
                    calendar.getTimeInMillis(),
                    100000,
                    createTimerAlarmStarterIntent(context));
        }
    }

    public static void cancelAlarmThatStartsTimerAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(createTimerAlarmStarterIntent(context));
        }
    }

    private static PendingIntent createTimerAlarmStarterIntent(Context context) {
        Intent intent = new Intent(context, BigClockWidgetBroadcast.class);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 200, intent,
                        PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }


}
