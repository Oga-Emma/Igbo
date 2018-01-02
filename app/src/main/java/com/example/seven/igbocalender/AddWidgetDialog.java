package com.example.seven.igbocalender;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by seven on 12/29/17.
 */

public class AddWidgetDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext())
                .inflate(R.layout.alert_dialog, null);

        TextView textView = v.findViewById(R.id.instruction_text_view);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setText(getContext().getResources().getString(R.string.add_widget_api21));
        }else if(android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT){
            textView.setText(getContext().getResources().getString(R.string.add_widget_api19));
        }else{
            textView.setText(getContext().getResources().getString(R.string.add_widget_api16));

        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(v)
                .setNegativeButton("Close", null);

        return dialog.create();
    }
}
