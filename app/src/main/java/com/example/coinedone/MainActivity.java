package com.example.coinedone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        CircularProgressBar circularProgressBar1 = (CircularProgressBar) findViewById(R.id.circularProgress);
//        circularProgressBar1.setProgress(25);
        circularProgressBar1.setProgressValue(25,12,25);
        circularProgressBar1.setProgressColor(Color.RED);
    }
}