package com.example.frelance0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Choose extends AppCompatActivity {


    Button FreelancerBtn;
    Button ClientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        FreelancerBtn = (Button) findViewById(R.id.fBtn);
        ClientBtn = (Button)  findViewById(R.id.cBtn);



        FreelancerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose.this, F_Sign_Up.class);

                startActivity(intent);
            }
        });

        ClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose.this, C_Sign_up.class);

                startActivity(intent);
            }
        });

    }
}