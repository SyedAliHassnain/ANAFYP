package com.example.android.anafyp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Registor extends AppCompatActivity {

    private EditText userName,userPassword,userEmail,userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    String age,name,password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_registor);
        setupUIViews();
        firebaseAuth=FirebaseAuth.getInstance();


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                //Upload Data to Database
                    String user_email=userEmail.getText().toString().trim();
                    String user_password=userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendUserData();
                                //sendEmailVerification();
                                firebaseAuth.signOut();
                                Toast.makeText(Registor.this,"Successfully Registered,Upload Complete!",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(Registor.this,MainActivity.class));
                            }
                        else{

                           Toast.makeText(Registor.this,"Registration Failed",Toast.LENGTH_LONG).show();

                            }

                        }

                    });

                }
            }
        });
    userLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Registor.this,MainActivity.class));
        }
    });
    }

    private void setupUIViews(){
        userName=(EditText)findViewById(R.id.etUserName);
        userPassword=(EditText)findViewById(R.id.etUserPassword);
        userEmail=(EditText)findViewById(R.id.etUserEmail);
        regButton=(Button)findViewById(R.id.etRegistor);
        userLogin=(TextView)findViewById(R.id.already);
        userAge=(EditText)findViewById(R.id.etAge);
    }
    private Boolean validate(){
        Boolean result=false;
        name=userName.getText().toString();
        password=userPassword.getText().toString();
        email=userEmail.getText().toString();
        age=userAge.getText().toString();


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty() ){
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_LONG).show();
        }
        else{
            result=true;
        }
        return result;
    }

    private void sendEmailVerification(){
        final FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   sendUserData();
                   Toast.makeText(Registor.this,"Successfully Registered,Verification Email Sent!",Toast.LENGTH_LONG).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(Registor.this,MainActivity.class));
               }
               else{
                   Toast.makeText(Registor.this,"Verification Email hasn't Sent!",Toast.LENGTH_LONG).show();

               }
                }
            });
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile= new UserProfile(age,email,name);

        myRef.setValue(userProfile);
    }


}

