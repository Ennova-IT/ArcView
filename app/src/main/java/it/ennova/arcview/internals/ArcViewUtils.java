package it.ennova.arcview.internals;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.NonNull;

public class ArcViewUtils {

    public static void updateSizeOf(@NonNull RectF rectF, @NonNull Canvas canvas) {
        if (canvas.getWidth() >= canvas.getHeight()) {
            setDimensionsHorizontal(rectF, canvas);
        } else {
            getDimensionsVertical(rectF, canvas);
        }
    }

    private static void setDimensionsHorizontal(@NonNull RectF rectF, @NonNull Canvas canvas) {
        final int deltaFactor = (canvas.getWidth() - canvas.getHeight()) / 2;
        rectF.set(deltaFactor, 0, deltaFactor + canvas.getHeight(), canvas.getHeight());
    }

    private static void getDimensionsVertical(@NonNull RectF rectF, @NonNull Canvas canvas) {
        final int deltaFactor = (canvas.getHeight() - canvas.getWidth()) / 2;
        rectF.set(0, deltaFactor, canvas.getWidth(), deltaFactor + canvas.getWidth());
    }
}

