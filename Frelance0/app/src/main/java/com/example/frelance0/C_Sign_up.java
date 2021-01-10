package com.example.frelance0;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class C_Sign_up extends AppCompatActivity {

    EditText mFirstName , mLastName , mEmail , mPassword  , mphone;


    ImageView ProfileImg;
    Button register;
    TextView mlogin;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;
    DatabaseReference C_Database;
    Uri resultUri;
    String ProfileimgUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__sign_up);



        //connect Xml

        mFirstName= findViewById(R.id.FirstName);
        mLastName= findViewById(R.id.LastName);
        mEmail= findViewById(R.id.email);
        mPassword= findViewById(R.id.password);
        mphone= findViewById(R.id.phone);
        register= findViewById(R.id.signUp);
        mlogin =findViewById(R.id.logIn);
        progressBar=findViewById(R.id.progressBar);


        ProfileImg =  findViewById(R.id.ProfilePic);

        ProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent , 1);
            }
        });












        mAuth =FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user != null) {

                    Intent intent = new Intent(com.example.frelance0.C_Sign_up.this, Client_Profile.class);
                    startActivity(intent);
                    return;

                }


            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();




                mAuth =FirebaseAuth.getInstance();


                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(com.example.frelance0.C_Sign_up.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        // if (task.isSuccessful()){
                        //Toast.makeText(F_Sign_Up.this, "Sign Up Error", Toast.LENGTH_SHORT).show();

                        if(TextUtils.isEmpty(Email)){

                            mEmail.setError("E-mail is Required");
                            return;
                        }

                        if(TextUtils.isEmpty(Password)){

                            mPassword.setError("Password is Required");
                            return;
                        }
                        if(Password.length() < 6){

                            mPassword.setError("Password Must Be More Than 6 Char ");
                            return;
                        }



                        if(mAuth.getCurrentUser() != null) {

                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference Current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients").child(user_id);
                            Current_user_db.setValue(true);

                            C_Database = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients").child(user_id);
                            Getinfo();
                            Saveinfo();

                            Intent intent = new Intent(com.example.frelance0.C_Sign_up.this, MainActivity2.class);
                            startActivity(intent);

                        }

                    }
                });





            }


        });







    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);

    }

    public void onClick(View view){

        Intent intent = new Intent(C_Sign_up.this, C_Sign_in.class);

        startActivity(intent);
    }


    public void Saveinfo(){

        String Email = mEmail.getText().toString().trim();
        String Password = mPassword.getText().toString().trim();
        String FirstName = mFirstName.getText().toString();
        String LastName =  mLastName.getText().toString();
        String FullName = mFirstName.getText().toString() + " " + mLastName.getText().toString();
        String PhoneNum= mphone.getText().toString();

        Map<String,Object> Map = new HashMap<>();

        Map userinfo = new HashMap();

        userinfo.put("First Name",FirstName);
        userinfo.put("Last Name",LastName);
        userinfo.put("Full Name",FullName);
        userinfo.put("Email",Email);
        userinfo.put("Phone Number" , PhoneNum);





        String user_id = mAuth.getCurrentUser().getUid();

        C_Database.updateChildren(userinfo);

        if(resultUri!= null){
            StorageReference FilePath = FirebaseStorage.getInstance().getReference().child("Profile images").child(user_id);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,20,baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = FilePath.putBytes(data);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    finish();
                    return;
                }
            });

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                    Map newImg = new HashMap();
                    newImg.put("ProfileImageUrl", downloadUrl.toString());
                    C_Database.updateChildren(newImg);
                    finish();
                    return;
                }
            });

        }else {

            finish();
        }





    }

    public void Getinfo(){
        C_Database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists() && datasnapshot.getChildrenCount()>0){

                    String Email = mEmail.getText().toString().trim();
                    String Password = mPassword.getText().toString().trim();
                    String FirstName = mFirstName.getText().toString();
                    String LastName =  mLastName.getText().toString();
                    // String FullName = mFirstName.getText().toString() + " " + mLastName.getText().toString();
                    String PhoneNum= mphone.getText().toString();

                    Map<String,Object> Map = new HashMap<>();





                    if(Map.get("First Name")!=null){
                        FirstName = Map.get("First Name").toString();
                        mFirstName.setText(FirstName);

                    }

                    if(Map.get("Email")!=null){
                        Email = Map.get("Email").toString();
                        mEmail.setText(Email);

                    }




                    if(Map.get("Password")!=null){
                        Password = Map.get("Password").toString();
                        mPassword.setText(Password);

                    }



                    if(Map.get("LastName")!=null){
                        LastName = Map.get("LastName").toString();
                        mLastName.setText(LastName);

                    }





                    if(Map.get("PhoneNum")!=null){
                        PhoneNum = Map.get("PhoneNum").toString();
                        mphone.setText(PhoneNum);

                    }








                    if(Map.get("ProfileImageUrl")!=null){

                        ProfileimgUrl = Map.get("ProfileImageUrl").toString();
                        Glide.with(getApplication()).load(ProfileimgUrl).into(ProfileImg);

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1 && requestCode == Activity.RESULT_OK);
        final Uri imgUri = data.getData();

        resultUri = imgUri;
        ProfileImg.setImageURI(imgUri);
    }

    public void OnClick(View view){

        Intent intent = new Intent(com.example.frelance0.C_Sign_up.this, C_Sign_in.class);

        startActivity(intent);
    }


}







