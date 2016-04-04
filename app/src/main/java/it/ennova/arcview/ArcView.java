package it.ennova.arcview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import it.ennova.arcview.internals.ArcViewUtils;


public class ArcView extends View implements View.OnTouchListener {
    private final static int NUMBER_OF_SLICES = 4;

    private static final int TOP = 0;
    private static final int LEFT = 0;
    private static final int BOTTOM = 800 + TOP;
    private static final int RIGHT = 800 + LEFT;

    private static final int HALF_SIDE = RIGHT / 2;
    private static final int HALF_HEIGHT = BOTTOM / 2;

    final boolean selected[] = new boolean[NUMBER_OF_SLICES];
    @ColorInt
    final int selectedColors[] = new int[NUMBER_OF_SLICES];
    @ColorInt
    final int unselectedColors[] = new int[NUMBER_OF_SLICES];

    private RectF targetRect = new RectF(0, 0, 0, 0);
    private final Paint targetPaint = new Paint();

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
        setOnTouchListener(this);
        initSelectedArrayTo(true, getContext().getResources().getIntArray(R.array.selected_colors),
                getContext().getResources().getIntArray(R.array.unselected_colors));
    }

    private void initSelectedArrayTo(boolean defaultValue, @ColorRes int[] selectedColors,
                                     @ColorRes int[] unselectedColors) {

        for (int i = 0; i < NUMBER_OF_SLICES; i++) {
            selected[i] = defaultValue;
            this.selectedColors[i] = selectedColors[i];
            this.unselectedColors[i] = unselectedColors[i];
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        ArcViewUtils.updateSizeOf(targetRect, canvas);
        targetPaint.setColor(Color.RED);
        targetPaint.setStyle(Paint.Style.FILL);
        targetPaint.setAntiAlias(true);
        targetPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawColor(Color.GRAY);

        for (int i = 0; i < NUMBER_OF_SLICES; i++) {
            drawArc(i, canvas);
        }
    }

    private void drawArc(int index, Canvas canvas) {
        if (selected[index]) {
            targetPaint.setColor(selectedColors[index]);
        } else {
            targetPaint.setColor(unselectedColors[index]);
        }
        canvas.drawArc(targetRect, index * 90, 90, true, targetPaint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int square = getVerticalQuadrantFrom(event.getY(), getInterestedQuadrantsFrom(event.getX()));

        if (square > -1) {
            selected[square] = !selected[square];
            invalidate();
        }

        return false;
    }

    private int getVerticalQuadrantFrom(float y, Pair<Integer, Integer> quadrants) {
        if (y >= TOP && y < HALF_HEIGHT) {
            return quadrants.first;
        } else if (y >= HALF_HEIGHT && y <= BOTTOM) {
            return quadrants.second;
        }

        return -1;
    }

    private Pair<Integer, Integer> getInterestedQuadrantsFrom(float x) {
        if (x >= HALF_SIDE && x <= RIGHT) {
            return new Pair<>(3,0);
        } else if (x >= LEFT && x < HALF_SIDE) {
            return new Pair<>(2,1);
        }

        return new Pair<>(-1, -1);
    }
}
