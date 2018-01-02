package com.example.seven.igbocalender;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int ids[] = AppWidgetManager.getInstance(this).getAppWidgetIds(new ComponentName(this,ClockWidget.class));

//        Toast.makeText(this, "Number of widgets: "+ids.length, Toast.LENGTH_LONG).show();

        if(ids.length < 1){
            AddWidgetDialog dialog = new AddWidgetDialog();
            dialog.show(getSupportFragmentManager(), "Add Widget");
        }

        DateFormat df = new SimpleDateFormat("hh:mm");
        DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy");
        DateFormat hour = new SimpleDateFormat("a");

        Date date = new Date();

        TextView time = findViewById(R.id.time_text_view);
        TextView timeOfDay = findViewById(R.id.time_of_day_text_view);
        TextView dateText = findViewById(R.id.date_text_view);
        TextView igbo1 = findViewById(R.id.igbo1_text_view);
        TextView igbo2 = findViewById(R.id.igbo2_text_view);

        time.setText(df.format(date));
        timeOfDay.setText(hour.format(date));
        dateText.setText(dateFormat.format(date));

    }


}
