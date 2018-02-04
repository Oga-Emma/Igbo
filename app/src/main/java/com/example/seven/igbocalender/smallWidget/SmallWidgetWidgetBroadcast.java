package com.example.seven.igbocalender.smallWidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;

import com.example.seven.igbocalender.R;
import com.example.seven.igbocalender.bigWidget.BigClockWidget;
import com.example.seven.igbocalender.bigWidget.BigCloclStartCLockAlarmManger;
import com.example.seven.igbocalender.model.DateData;
import com.example.seven.igbocalender.repositoory.DataStore;
import com.example.seven.igbocalender.utility.TextViewUtils;
import com.example.seven.igbocalender.utility.Utils;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by seven on 12/24/17.
 */

public class SmallWidgetWidgetBroadcast extends BroadcastReceiver {

    public static final String FIRST_RUN = "first_run";
    DateFormat df = new SimpleDateFormat("hh:mm");
    DateFormat hour = new SimpleDateFormat("a");
    Date date;
    String action = "";

    @Override
    public void onReceive(Context context, Intent intent) {
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
                }
            }
        }
    }
}
