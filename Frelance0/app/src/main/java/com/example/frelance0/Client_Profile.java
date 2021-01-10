package com.example.frelance0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Client_Profile extends AppCompatActivity {


    TextView mEmail   , mphone;
    TextView FullName;
    String user_id;
    ImageView ProfileImg;
    String ProfileImgeurl;

    FirebaseAuth mAuth;
    DatabaseReference C_Database;
    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer__profile);

//connect XML

        FullName= (TextView) findViewById(R.id.JobTitle);

        mEmail= (TextView) findViewById(R.id.email);
        mphone= (TextView) findViewById(R.id.phone);
        mAuth =FirebaseAuth.getInstance();
        ProfileImg = findViewById(R.id.ProfilePic);

        if(mAuth.getCurrentUser() != null){

            String user_id = mAuth.getCurrentUser().getUid();
            Log.d("user id", user_id);

            DatabaseReference Current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients").child(user_id);

            C_Database = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients").child("user_id");
            Log.d("database",C_Database.toString());

            GetFreelancerinfo(Current_user_db);



        }






    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Choose.class));
        finish();

    }


    public  void  GetFreelancerinfo(DatabaseReference current_user){
        current_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String , Object> map = (Map<String, Object>) dataSnapshot.getValue();


                    if(map.get("Email")!=null){

                        mEmail.setText(map.get("Email").toString());


                    }



                    if(map.get("Phone Number")!=null){

                        mphone.setText(map.get("Phone Number").toString());


                    }




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

