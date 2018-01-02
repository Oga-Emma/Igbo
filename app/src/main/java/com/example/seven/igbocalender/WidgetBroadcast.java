package com.example.seven.igbocalender;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by seven on 12/24/17.
 */

public class WidgetBroadcast extends BroadcastReceiver {

    static DateFormat df = new SimpleDateFormat("hh:mm");
    static DateFormat hour = new SimpleDateFormat("a");
    static Date date;

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
        //Acquire the lock
        wl.acquire();

        date = new Date();

        Log.i("CLOCK WIDGET RECEIVER", "broadcast received");

//        You can do the processing here update the widget/remote views.

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.clock_widget);
//        remoteViews.setTextViewText(R.id.tvTime, Utility.getCurrentTime("hh:mm:ss a"));

        remoteViews.setTextViewText(R.id.timer_text_view, df.format(date));
        remoteViews.setTextViewText(R.id.time_of_day_text_view, hour.format(date));

        ComponentName thiswidget = new ComponentName(context, ClockWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thiswidget, remoteViews);



        //Release the lock
        wl.release();
    }
}
