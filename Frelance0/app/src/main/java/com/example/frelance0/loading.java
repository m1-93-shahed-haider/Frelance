package com.example.frelance0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class loading extends AppCompatActivity {

    private Handler mHandler = new Handler();

    ImageView Congrats ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Congrats = findViewById(R.id.Congrats);

        mHandler.postDelayed(new Runnable() {
            public void run() {
                load();
            }
        }, 1000);



}


    private void load() {
        Intent intent = new Intent(loading.this, Freelancer_Profile.class);

        startActivity(intent);
    }



}