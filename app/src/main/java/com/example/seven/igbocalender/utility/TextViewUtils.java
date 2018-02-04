package com.example.seven.igbocalender.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.TypedValue;

/**
 * Created by seven on 1/19/18.
 */

public class TextViewUtils {

    public static Bitmap getFontBitmap(Context context, String text) {

        Bitmap myBitmap = Bitmap.createBitmap(160, 84, Bitmap.Config.ARGB_8888);
        Canvas myCanvas = new Canvas(myBitmap);
        Paint paint = new Paint();
        Typeface clock = Typeface.createFromAsset(context.getAssets(), "Fonts/Roboto-Regular.ttf");
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTypeface(clock);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(65);
        paint.setTextAlign(Paint.Align.CENTER);
        myCanvas.drawText(text, 80, 60, paint);
        return myBitmap;
//
//        int fontSizePX = convertDiptoPix(context, fontSizeSP);
//        int pad = (fontSizePX / 9);
//        Paint paint = new Paint();
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Fonts/Roboto-Regular.ttf");
//        paint.setAntiAlias(true);
//        paint.setTypeface(typeface);
//        paint.setColor(color);
//        paint.setTextSize(fontSizePX);
//
//        int textWidth = (int) (paint.measureText(text) + pad * 2);
//        int height = (int) (fontSizePX / 0.75);
//        Bitmap bitmap = Bitmap.createBitmap(textWidth, height, Bitmap.Config.ARGB_4444);
//        Canvas canvas = new Canvas(bitmap);
//        float xOriginal = pad;
//        canvas.drawText(text, xOriginal, fontSizePX, paint);
//        return bitmap;
    }

    public static Bitmap getFontBitmapNoPadding(Context context, String text) {

        Bitmap myBitmap = Bitmap.createBitmap(160, 84, Bitmap.Config.ARGB_8888);
        Canvas myCanvas = new Canvas(myBitmap);
        Paint paint = new Paint();
        Typeface clock = Typeface.createFromAsset(context.getAssets(), "Fonts/Roboto-Regular.ttf");
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTypeface(clock);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(65);
        paint.setTextAlign(Paint.Align.CENTER);
        myCanvas.drawText(text, 80, 60, paint);
        return myBitmap;
//
//        int fontSizePX = convertDiptoPix(context, fontSizeSP);
//        int pad = (fontSizePX / 9);
//        Paint paint = new Paint();
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Fonts/Roboto-Regular.ttf");
//        paint.setAntiAlias(true);
//        paint.setTypeface(typeface);
//        paint.setColor(color);
//        paint.setTextSize(fontSizePX);
//
//        int textWidth = (int) (paint.measureText(text) + pad * 2);
//        int height = (int) (fontSizePX / 0.75);
//        Bitmap bitmap = Bitmap.createBitmap(textWidth, height, Bitmap.Config.ARGB_4444);
//        Canvas canvas = new Canvas(bitmap);
//        float xOriginal = pad;
//        canvas.drawText(text, xOriginal, fontSizePX, paint);
//        return bitmap;
    }

    public static Bitmap getFontBitmap(Context context, String text, int color, float fontSizeSP) {
        int fontSizePX = convertDiptoPix(context, fontSizeSP);
        int pad = (fontSizePX / 9);
        Paint paint = new Paint();
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Fonts/Roboto-Regular.ttf");
        paint.setAntiAlias(true);
        paint.setTypeface(typeface);
//        paint.setColor(color);
        paint.setColor(Color.WHITE);
        paint.setTextSize(fontSizePX);

        int textWidth = (int) (paint.measureText(text) + pad * 2);
        int height = (int) (fontSizePX / 0.75);
        Bitmap bitmap = Bitmap.createBitmap(textWidth, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        float xOriginal = pad;
        canvas.drawText(text, xOriginal, fontSizePX, paint);
        return bitmap;
    }

    public static Bitmap getFontBitmap(Context context, String text, float fontSizeSP) {
        int fontSizePX = convertDiptoPix(context, fontSizeSP);
        int pad = (fontSizePX / 9);
        Paint paint = new Paint();
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Fonts/Roboto-Regular.ttf");
        paint.setAntiAlias(true);
        paint.setTypeface(typeface);
//        paint.setColor(color);
        paint.setColor(Color.WHITE);
        paint.setTextSize(fontSizePX);

        int textWidth = (int) (paint.measureText(text) + pad * 2);
        int height = (int) (fontSizePX / 0.75);
        Bitmap bitmap = Bitmap.createBitmap(textWidth, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        float xOriginal = pad;
        canvas.drawText(text, xOriginal, fontSizePX, paint);
        return bitmap;
    }

    public static int convertDiptoPix(Context context, float dip) {
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return value;
    }
}
