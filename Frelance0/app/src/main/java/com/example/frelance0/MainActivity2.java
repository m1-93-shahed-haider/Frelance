package com.example.frelance0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    DatabaseReference F_Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseAuth mAuth;
        mAuth =FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        F_Database = FirebaseDatabase.getInstance().getReference().child("Users").child("Freelancers").child(user_id);
        ArrayList <Users> UsersLine = new ArrayList<>();

        RecyclerView UsersList = findViewById(R.id.rv);
        UsersList.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        UsersList.setLayoutManager(lm);

        Adapter user = new Adapter(UsersLine,this);
        UsersList.setAdapter(user);

        //Adapter user  = new Adapter(UsersLine,this);

        Query allUsers = F_Database.child("Freelancers");

        allUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for(DataSnapshot Freelancers :datasnapshot.getChildren()){
                    Users p = Freelancers.getValue(Users.class);
                    UsersLine.add(p);
                    user.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //Toast.makeText(MainActivity2.this,databaseError.)

            }
        });








    }

    public void m2(View view){

        Intent intent = new Intent(MainActivity2.this, Freelancer_Profile.class);

        startActivity(intent);
    }

}