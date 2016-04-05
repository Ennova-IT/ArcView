package it.ennova.arcview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int[] selectedColors1;
    int[] selectedColors2;
    int[] unselectedColors1;
    int[] unselectedColors2;

    private static final String FIRST_KEY = "isFirst";
    private boolean isFirst = true;

    private ArcView arcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arcView = (ArcView) findViewById(R.id.arcView);

        selectedColors1 = getResources().getIntArray(R.array.arcview_default_selected_colors);
        selectedColors2 = getResources().getIntArray(R.array.selected_colors);
        unselectedColors1 = getResources().getIntArray(R.array.arcview_default_unselected_colors);
        unselectedColors2 = getResources().getIntArray(R.array.unselected_colors);
    }

    public void changeThings(View view) {
        if (isFirst) {
            arcView.updateColorsWith(selectedColors2, unselectedColors2);
            isFirst = false;
        } else {
            arcView.updateColorsWith(selectedColors1, unselectedColors1);
            isFirst = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(FIRST_KEY, isFirst);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isFirst = savedInstanceState.getBoolean(FIRST_KEY, isFirst);
    }
}
