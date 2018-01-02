package com.example.seven.igbocalender;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Implementation of App Widget functionality.
 */
public class ClockWidget extends AppWidgetProvider {

    private static final String LOG_TAG = "Clock Widget";
    public static String CLOCK_WIDGET_UPDATE = "com.example.seven.igbocalender.WIDGET_UPDATE";

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d(LOG_TAG, "Received intent " + intent);
//        if (CLOCK_WIDGET_UPDATE.equals(intent.getAction())) {
//            Log.d(LOG_TAG, "Clock update");
//
//            // Get the widget manager and ids for this widget provider, then call the shared
//            // clock update method.
//            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), getClass().getName());
//            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//            int ids[] = appWidgetManager.getAppWidgetIds(thisAppWidget);
//            for (int appWidgetID: ids) {
//                updateAppWidget(context, appWidgetManager, appWidgetID);
//            }
//        }else
            super.onReceive(context, intent);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        DateFormat df = new SimpleDateFormat("hh:mm");
        DateFormat hour = new SimpleDateFormat("a");

        Date date = new Date();

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.clock_widget);
        views.setTextViewText(R.id.timer_text_view, df.format(date));
        views.setTextViewText(R.id.time_of_day_text_view, hour.format(date));
        views.setTextViewText(R.id.date_text_view, Utils.getDateFormat().format(date));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    private PendingIntent createClockTickIntent(Context context) {
        Intent intent = new Intent(context, WidgetBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        return pendingIntent;
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "Widget Provider enabled.  Starting timer to update widget every second");
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 60000
                    , createClockTickIntent(context));
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "Widget Provider disabled. Turning off timer");
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(createClockTickIntent(context));
        }
    }
}

