package it.ennova.arcview.internals;


import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

public class ContextCompat {

    @SuppressWarnings("deprecation")
    @ColorInt
    public static int getColor(@NonNull Context context, @ColorRes int colorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorRes);
        } else {
            return context.getResources().getColor(colorRes);
        }
    }
}
