package com.example.seven.igbocalender;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seven.igbocalender.bigWidget.BigClockWidget;
import com.example.seven.igbocalender.model.DateData;
import com.example.seven.igbocalender.repositoory.DataStore;
import com.example.seven.igbocalender.utility.AddWidgetDialog;
import com.example.seven.igbocalender.utility.Utils;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    final DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy");
    final DateFormat hour = new SimpleDateFormat("a");
    TextView time, timeOfDay, dateText, igbo1, igbo2;
    ImageView moonImage;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.date_text_view);
        igbo1 = findViewById(R.id.igbo1_text_view);
        igbo2 = findViewById(R.id.igbo2_text_view);
        moonImage = findViewById(R.id.moon_image_view);

        final int ids[] = AppWidgetManager.getInstance(MainActivity.this)
                .getAppWidgetIds(new ComponentName(MainActivity.this, BigClockWidget.class));

        final DateData data = DataStore.getData(LocalDate.now());

        if (data != null) {
            if (ids.length < 1) {
                AddWidgetDialog dialog = new AddWidgetDialog();
                dialog.show(getSupportFragmentManager(), "Add Widget");
            }

            Date date = new Date();

            dateText.setText(dateFormat.format(date));

            igbo1.setText(data.getOnwa_izu_asaa());

            String marketDay = data.getTrad_week() + ", " + data.getTrad_day()
                    + " " + data.getTrad_month();

            igbo2.setText(marketDay);

            moonImage.setImageResource(Utils.getMoonImageResource(data.getTrad_day()));

        }
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
