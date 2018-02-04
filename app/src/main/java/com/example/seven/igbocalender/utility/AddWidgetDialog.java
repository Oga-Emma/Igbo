package com.example.seven.igbocalender.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.seven.igbocalender.R;

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

        textView.setText(getContext().getResources().getString(R.string.add_widget_instruction));

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(v)
                .setNegativeButton("Close", null);

        return dialog.create();
    }
}
