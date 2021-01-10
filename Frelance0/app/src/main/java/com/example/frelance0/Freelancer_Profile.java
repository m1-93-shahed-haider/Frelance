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

public class Freelancer_Profile extends AppCompatActivity {

    //Bundle B = getIntent().getExtras();
    //Users M = (Users) B.getSerializable("user");


    TextView mFirstName , mLastName , mEmail   , mphone;
    TextView FullName;
    TextView insta , behance , website ;
    TextView Skill1 , Skill2 , Skill3  , Skill4   , Brief , jobTitle , Software1 , Software2 , Software3 , Software4;
    String user_id;
    ImageView ProfileImg;
    String ProfileImgeurl;

    FirebaseAuth mAuth;
    DatabaseReference F_Database;
    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer__profile);

//connect XML
        mFirstName= (TextView) findViewById(R.id.FirstName);
        mLastName= (TextView) findViewById(R.id.LastName);
        FullName= (TextView) findViewById(R.id.JobTitle);

        mEmail= (TextView) findViewById(R.id.email);
        mphone= (TextView) findViewById(R.id.phone);
        Skill1 = (TextView) findViewById(R.id.Skill1);
        Skill2 = (TextView) findViewById(R.id.Skill2);
        Skill3 = (TextView) findViewById(R.id.Skill3);
        Skill4 = (TextView) findViewById(R.id.Skill4);
        Software1 = (TextView) findViewById(R.id.software1);
        Software2 = (TextView) findViewById(R.id.software2);
        Software3 = (TextView) findViewById(R.id.software3);
        Software4 =(TextView) findViewById(R.id.Software4);
        jobTitle = (TextView) findViewById(R.id.FullName);
        Brief = (TextView) findViewById(R.id.smallBrief);
        insta = (TextView) findViewById(R.id.insta);
        website = (TextView) findViewById(R.id.Website);
        behance = (TextView) findViewById(R.id.Behance);
        mAuth =FirebaseAuth.getInstance();
        ProfileImg = findViewById(R.id.ProfilePic);



        String user_id = mAuth.getCurrentUser().getUid();
        Log.d("user id", user_id);

        DatabaseReference Current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Freelancers").child(user_id);

        F_Database = FirebaseDatabase.getInstance().getReference().child("Users").child("Freelancers").child("user_id");
        Log.d("database",F_Database.toString());

        GetFreelancerinfo(Current_user_db);






    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Freelancer_Log_in.class));
        finish();

    }


    public  void  GetFreelancerinfo(DatabaseReference current_user){
        current_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String , Object> map = (Map<String, Object>) dataSnapshot.getValue();

                    if(map.get("Brief")!=null) Brief.setText(map.get("Brief").toString());
                    else  Brief.setText("N/A");


                    if(map.get("Email")!=null){

                        mEmail.setText(map.get("Email").toString());


                    }

                    if(map.get("Job Title")!=null){

                        jobTitle.setText(map.get("Job Title").toString());


                    }

                    if(map.get("Phone Number")!=null){

                        mphone.setText(map.get("Phone Number").toString());


                    }

                    if(map.get("Behance")!=null){

                        behance.setText(map.get("Behance").toString());


                    }
                    if(map.get("Web")!=null){

                        website.setText(map.get("Web").toString());


                    }

                    if(map.get("Instagram")!=null){

                        insta.setText(map.get("Instagram").toString());


                    }

                    if(map.get("Skill 1")!=null){

                        Skill1.setText(map.get("Skill 1").toString());


                    }


                    if(map.get("Skill 2")!=null){

                        Skill2.setText(map.get("Skill 2").toString());


                    }


                    if(map.get("Skill 3")!=null){

                        Skill3.setText(map.get("Skill 3").toString());


                    }

                    if(map.get("Skill 4")!=null){

                        Skill4.setText(map.get("Skill 4").toString());


                    }


                    if(map.get("Software 1")!=null){

                        Software1.setText(map.get("Software 1").toString());


                    }


                    if(map.get("Software 2")!=null){

                        Software2.setText(map.get("Software 2").toString());


                    }


                    if(map.get("Software 3")!=null){

                        Software3.setText(map.get("Software 3").toString());


                    }


                    if(map.get("Software 4")!=null){

                        Software4.setText(map.get("Software 4").toString());


                    }











                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

