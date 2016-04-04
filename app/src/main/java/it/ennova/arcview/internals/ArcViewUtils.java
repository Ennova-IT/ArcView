package it.ennova.arcview.internals;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

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

    public static int getQuadrantIndexFrom(@NonNull View view, @NonNull MotionEvent event) {
        return getVerticalQuadrantFrom(view, event.getY(), getInterestedQuadrantsFrom(view, event.getX()));
    }

    private static Pair<Integer, Integer> getInterestedQuadrantsFrom(@NonNull View view, float x) {
        final int RIGHT = view.getWidth();
        final int HALF_SIDE = RIGHT / 2;

        if (x >= HALF_SIDE && x <= RIGHT) {
            return new Pair<>(3, 0);
        } else if (x >= 0 && x < HALF_SIDE) {
            return new Pair<>(2, 1);
        }

        return new Pair<>(-1, -1);
    }

    private static int getVerticalQuadrantFrom(@NonNull View view, float y, Pair<Integer, Integer> quadrants) {
        final int BOTTOM = view.getHeight();
        final int HALF_HEIGHT = BOTTOM / 2;

        if (y >= 0 && y < HALF_HEIGHT) {
            return quadrants.first;
        } else if (y >= HALF_HEIGHT && y <= BOTTOM) {
            return quadrants.second;
        }

        return -1;
    }
}

