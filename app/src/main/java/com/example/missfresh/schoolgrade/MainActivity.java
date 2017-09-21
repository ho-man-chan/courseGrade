package com.example.missfresh.schoolgrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.class";

    /**
     * Activity name
     */
    private final String TITLE = "390 App";


    /**
     * Component List
     */
    private Button showGradeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    /**
     *  Link component and its listeners
     */
    private void setupUI() {
        showGradeButton = (Button) findViewById(R.id.showGradeButton);
        showGradeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showGradeActivity();
            }
        });
    }

    /**
     * Shows grade activity
     */
    private void showGradeActivity() {
        Intent intent = new Intent(this, GradeActivity.class);
        startActivity(intent);
    }

}
