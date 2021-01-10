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
import com.example.frelance0.C_Sign_in;
import com.example.frelance0.Client_Profile;
import com.example.frelance0.Freelancer_Log_in;
import com.example.frelance0.Freelancer_Profile;
import com.example.frelance0.R;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Edit_Profile extends AppCompatActivity {

    EditText mFirstName , mLastName , mEmail  , mphone;
    EditText insta , behance , website ;
    EditText Skill1 , Skill2 , Skill3  , Skill4   , Brief , jobTitle , Software1 , Software2 , Software3 , Software4;

    ImageView ProfileImg;
    String ProfileImgeurl;

    Button confirm;

    ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;
    DatabaseReference F_Database;
    Uri resultUri;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f__sign__up);



        //connect Xml

        mFirstName= (EditText) findViewById(R.id.FirstName);
        mLastName= (EditText) findViewById(R.id.LastName);
        mEmail= (EditText) findViewById(R.id.email);

        mphone= (EditText) findViewById(R.id.phone);
        confirm= (Button) findViewById(R.id.Confirm);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        Skill1 = (EditText) findViewById(R.id.skill1);
        Skill2 = (EditText) findViewById(R.id.skill2);
        Skill3 = (EditText) findViewById(R.id.skill3);
        Skill4 = (EditText) findViewById(R.id.skill4);
        Software1 = (EditText) findViewById(R.id.software1);
        Software2 = (EditText) findViewById(R.id.software2);
        Software3 = (EditText) findViewById(R.id.software3);
        Software4 =(EditText) findViewById(R.id.software4);
        jobTitle = (EditText) findViewById(R.id.JobTiTleHint);
        Brief = (EditText) findViewById(R.id.Brief);

        ProfileImg = findViewById(R.id.ProfilePic);

        ProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent , 1);
            }
        });




        insta = (EditText) findViewById(R.id.insta);
        behance = (EditText) findViewById(R.id.Behance);
        website = (EditText) findViewById(R.id.Website);









        mAuth =FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user != null) {

                    Intent intent = new Intent(com.example.frelance0.Edit_Profile.this, Freelancer_Profile.class);
                    startActivity(intent);
                    return;

                }


            }
        };

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = mEmail.getText().toString().trim();





                mAuth =FirebaseAuth.getInstance();


                progressBar.setVisibility(View.VISIBLE);

              //Here confirm on click liste


                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference Current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Freelancers").child(user_id);
                            Current_user_db.setValue(true);

                            F_Database = FirebaseDatabase.getInstance().getReference().child("Users").child("Freelancers").child(user_id);
                            Getinfo();
                            Saveinfo();

                            Intent intent = new Intent(com.example.frelance0.Edit_Profile.this, Freelancer_Profile.class);
                            startActivity(intent);






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

    public void Saveinfo(){

        String Email = mEmail.getText().toString().trim();

        String FirstName = mFirstName.getText().toString();
        String LastName =  mLastName.getText().toString();
        String FullName = mFirstName.getText().toString() + " " + mLastName.getText().toString();
        String PhoneNum= mphone.getText().toString();
        String  Fsoftware= Software1.getText().toString();
        String  ssoftware= Software2.getText().toString();
        String  tsoftware= Software3.getText().toString();
        String  Ffsoftware= Software4.getText().toString();
        String  Fskill= Skill1.getText().toString();
        String  sskill= Skill2.getText().toString();
        String  tskill= Skill3.getText().toString();
        String  Ffskill= Skill4.getText().toString();
        String  JB= jobTitle.getText().toString();
        String  B= Brief.getText().toString();
        String Be = behance.getText().toString();
        String instag = insta.getText().toString();
        String web = website.getText().toString();
        Map<String,Object> Map = new HashMap<>();

        Map userinfo = new HashMap();

        userinfo.put("First Name",FirstName);
        userinfo.put("Last Name",LastName);
        userinfo.put("Full Name",FullName);
        userinfo.put("Email",Email);
        //Skills
        userinfo.put("Skill 1",Fskill);
        userinfo.put("Skill 2",sskill);
        userinfo.put("Skill 3",tskill);
        userinfo.put("Skill 4",Ffskill);

        //Softwares
        userinfo.put("Software 1",Fsoftware);
        userinfo.put("Software 2",ssoftware);
        userinfo.put("Software 3",tsoftware);
        userinfo.put("Software 4",Ffsoftware);


        //other
        userinfo.put("Brief",B);
        userinfo.put("Job Title",JB);



        String user_id = mAuth.getCurrentUser().getUid();

        F_Database.updateChildren(userinfo);

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
                    F_Database.updateChildren(newImg);
                    finish();
                    return;
                }
            });

        }else {

            finish();
        }





    }

    public void Getinfo(){
        F_Database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists() && datasnapshot.getChildrenCount()>0){

                    String Email = mEmail.getText().toString().trim();

                    String FirstName = mFirstName.getText().toString();
                    String LastName =  mLastName.getText().toString();
                    // String FullName = mFirstName.getText().toString() + " " + mLastName.getText().toString();
                    String PhoneNum= mphone.getText().toString();
                    String  Fsoftware= Software1.getText().toString();
                    String  ssoftware= Software2.getText().toString();
                    String  tsoftware= Software3.getText().toString();
                    String  Ffsoftware= Software4.getText().toString();
                    String  Fskill= Skill1.getText().toString();
                    String  sskill= Skill2.getText().toString();
                    String  tskill= Skill3.getText().toString();
                    String  Ffskill= Skill4.getText().toString();
                    String  JB= jobTitle.getText().toString();
                    String  B= Brief.getText().toString();
                    String Be = behance.getText().toString();
                    String instag = insta.getText().toString();
                    String web = website.getText().toString();
                    Map<String,Object> Map = new HashMap<>();





                    if(Map.get("First Name")!=null){
                        FirstName = Map.get("First Name").toString();
                        mFirstName.setText(FirstName);

                    }

                    if(Map.get("Email")!=null){
                        Email = Map.get("Email").toString();
                        mEmail.setText(Email);

                    }







                    if(Map.get("LastName")!=null){
                        LastName = Map.get("LastName").toString();
                        mLastName.setText(LastName);

                    }





                    if(Map.get("PhoneNum")!=null){
                        PhoneNum = Map.get("PhoneNum").toString();
                        mphone.setText(PhoneNum);

                    }




                    if(Map.get("Fsoftware")!=null){
                        Fsoftware = Map.get("Fsoftware").toString();
                        Software1.setText(Fsoftware);

                    }



                    if(Map.get("ssoftware")!=null){
                        ssoftware = Map.get("ssoftware").toString();
                        Software2.setText(ssoftware);

                    }




                    if(Map.get("tsoftware")!=null){
                        tsoftware= Map.get("ssoftware").toString();
                        Software3.setText(tsoftware);

                    }




                    if(Map.get("Ffsoftware")!=null){
                        Ffsoftware = Map.get("Ffsoftware").toString();
                        Software4.setText(Ffsoftware);

                    }



                    if(Map.get("Fskill")!=null){
                        Fskill = Map.get("First Name").toString();
                        Skill1.setText(Fskill);

                    }




                    if(Map.get("sskill")!=null){
                        sskill = Map.get("First Name").toString();
                        Skill2.setText(sskill);

                    }




                    if(Map.get("tskill")!=null){
                        tskill = Map.get("tskill").toString();
                        Skill3.setText(tskill);

                    }




                    if(Map.get("Ffskill")!=null){
                        Ffskill= Map.get("Ffskill").toString();
                        Skill4.setText(Ffskill);

                    }




                    if(Map.get("JB")!=null){
                        JB = Map.get("JB").toString();
                        jobTitle.setText(JB);

                    }




                    if(Map.get("B")!=null){
                        B = Map.get("B").toString();
                        Brief.setText(B);

                    }


                    if(Map.get("Be")!=null){
                        Be = Map.get("Be").toString();
                        behance.setText(Be);

                    }


                    if(Map.get("instag")!=null){
                        instag = Map.get("instag").toString();
                        insta.setText(instag);

                    }


                    if(Map.get("web")!=null){
                        web = Map.get("web").toString();
                        website.setText(web);

                    }



                    if(Map.get("ProfileImageUrl")!=null){

                        ProfileImgeurl = Map.get("ProfileImageUrl").toString();
                        Glide.with(getApplication()).load(ProfileImgeurl).into(ProfileImg);

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
}





