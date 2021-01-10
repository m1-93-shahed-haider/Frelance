package com.example.frelance0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mHandler.postDelayed(new Runnable() {
            public void run() {
                doStuff();
            }
        }, 1000);

    }



    private void doStuff() {
        Intent intent = new Intent(MainActivity.this, Choose.class);

        startActivity(intent);
    }
}