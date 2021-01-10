package com.example.frelance0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Freelancer_Log_in extends AppCompatActivity {

    EditText mEmail , mPassword;
    Button mSignIn;
    ProgressBar progressbar;
    TextView mSignup;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer__log_in);

        mEmail= findViewById(R.id.email);
        mPassword= findViewById(R.id.password);
        mSignIn= findViewById(R.id.signIn);
        mSignup=findViewById(R.id.signUp);
        progressbar=findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {

                    mEmail.setError("E-mail is Required");
                    return;
                }

                if (TextUtils.isEmpty(Password)) {

                    mPassword.setError("Password is Required");
                    return;
                }
                if (Password.length() < 6) {

                    mPassword.setError("Password Must Be More Than 6 Char ");
                    return;
                }

                mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {



                            Toast.makeText(Freelancer_Log_in.this, "Loged in Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Freelancer_Log_in.this, Freelancer_Profile.class);

                            startActivity(intent);
                            //startActivity(new Intent(getApplicationContext(),sign_in.class));
                            //finish();
                            // Log.d("SUCCESSFUL " , intent.toString());


                        } else {
                            Log.d("ERROR ", task.getException().getMessage());

                            Toast.makeText(Freelancer_Log_in.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                });


            }


        });






    }

    public void onClick(View view){

        Intent intent = new Intent(Freelancer_Log_in.this, F_Sign_Up.class);

        startActivity(intent);
    }
}