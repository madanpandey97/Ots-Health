package com.example.kpmadan.health_ots;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignupOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_option);
    }
    public void signUpWithEmail(View v){
        Intent intent = new Intent(SignupOptionActivity.this, EmailRegisterActivity.class);
        startActivity(intent);
    }
    public void signUpWithPhone(View v){
        Intent intent = new Intent(SignupOptionActivity.this, SIgnInWithPhoneActivity.class);
        startActivity(intent);
    }
}
