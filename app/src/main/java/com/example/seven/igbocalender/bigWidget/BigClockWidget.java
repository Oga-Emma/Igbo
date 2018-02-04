package com.example.seven.igbocalender.bigWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.seven.igbocalender.MainActivity;
import com.example.seven.igbocalender.R;
import com.example.seven.igbocalender.model.DateData;
import com.example.seven.igbocalender.repositoory.DataStore;
import com.example.seven.igbocalender.utility.TextViewUtils;
import com.example.seven.igbocalender.utility.Utils;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class BigClockWidget extends AppWidgetProvider {

    private static final String LOG_TAG = "Clock Widget";
    public static String CLOCK_WIDGET_UPDATE = "com.example.seven.igbocalender.WIDGET_UPDATE";

    DateFormat df = new SimpleDateFormat("hh:mm");
    DateFormat hour = new SimpleDateFormat("a");
    Date date = null;
    String action = "";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Log.i(LOG_TAG, "on update called");

        DateFormat df = new SimpleDateFormat("hh:mm");
        DateFormat hour = new SimpleDateFormat("a");

        Date date = new Date();

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.big_clock_widget);
        views.setImageViewBitmap(R.id.time_image_view,
                TextViewUtils.getFontBitmap(context, df.format(date), 60f));
        views.setImageViewBitmap(R.id.time_of_day_image_view,
                TextViewUtils.getFontBitmap(context, hour.format(date), 16f));
        views.setImageViewBitmap(R.id.date_image_view,
                TextViewUtils.getFontBitmap(context, Utils.getDateFormat().format(date), 16f));

        DateData data = DataStore.getData(LocalDate.now());

        String marketDay = data.getTrad_week() + ", " + data.getTrad_day()
                + " " + data.getTrad_month();

        views.setImageViewBitmap(R.id.onwa_izu_asa_image_view,
                TextViewUtils.getFontBitmap(context, data.getOnwa_izu_asaa(), 18f));

        views.setImageViewBitmap(R.id.market_day_image_view,
                TextViewUtils.getFontBitmap(context, marketDay, 18f));

        views.setImageViewResource(R.id.moon_image_view,
                Utils.getMoonImageResource(data.getTrad_day()));

        Intent configIntent = new Intent(context, MainActivity.class);
        PendingIntent configPendingIntent =
                PendingIntent.getActivity(context, 0, configIntent, 0);

        views.setOnClickPendingIntent(R.id.bigwidget_fragment_layout, configPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        action = intent.getAction();

        if (null != action) {
            if ((intent.getAction().equals(Intent.ACTION_USER_PRESENT) ||
                    intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))) {
                // Set the alarm here.
                BigClockAlarmManager.startTimerAlarm(context);
                BigCloclStartCLockAlarmManger.createAlarmToStartTimerAlarm(context);
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

                BigCloclStartCLockAlarmManger.cancelAlarmThatStartsTimerAlarm(context);
                BigClockAlarmManager.cancelTimerAlarm(context);
            }
        }

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        if (pm.isScreenOn()) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");

            wl.acquire();

            date = new Date();

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.big_clock_widget);

            remoteViews.setImageViewBitmap(R.id.time_image_view,
                    TextViewUtils.getFontBitmap(context, df.format(date), 60f));
            remoteViews.setImageViewBitmap(R.id.time_of_day_image_view,
                    TextViewUtils.getFontBitmap(context, hour.format(date), 16f));

            remoteViews.setImageViewBitmap(R.id.date_image_view,
                    TextViewUtils.getFontBitmap(context, Utils.getDateFormat().format(date), 16f));

            DateData data = DataStore.getData(LocalDate.now());

            String marketDay = data.getTrad_week() + ", " + data.getTrad_day()
                    + " " + data.getTrad_month();

            remoteViews.setImageViewBitmap(R.id.onwa_izu_asa_image_view,
                    TextViewUtils.getFontBitmap(context, data.getOnwa_izu_asaa(), 18f));

            remoteViews.setImageViewBitmap(R.id.market_day_image_view,
                    TextViewUtils.getFontBitmap(context, marketDay, 18f));

            remoteViews.setImageViewResource(R.id.moon_image_view,
                    Utils.getMoonImageResource(data.getTrad_day()));


            ComponentName thiswidget = new ComponentName(context, BigClockWidget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(thiswidget, remoteViews);

            //Release the lock
            wl.release();

            action = intent.getAction();

            if (action != null && !action.isEmpty()) {
                if (intent.getAction().equals(Intent.ACTION_USER_PRESENT) ||
                        intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

                    BigCloclStartCLockAlarmManger.createAlarmToStartTimerAlarm(context);
                    BigClockAlarmManager.startTimerAlarm(context);
                }
            }
        }

        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "Widget Provider enabled.  Starting timer to update widget every second");
        BigCloclStartCLockAlarmManger.createAlarmToStartTimerAlarm(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "Widget Provider disabled. Turning off timer");

        BigCloclStartCLockAlarmManger.cancelAlarmThatStartsTimerAlarm(context);
    }
}

