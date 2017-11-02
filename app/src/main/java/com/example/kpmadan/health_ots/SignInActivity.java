package com.example.kpmadan.health_ots;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button btn = (Button) findViewById(R.id.signinButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(SignInActivity.this, LoginWithEmailActivity.class);
                startActivity(intent);
            }
        });


    }

    public void onClickSingUp(View v)
    {
        Intent intent = new Intent(SignInActivity.this, SignupOptionActivity.class);
        startActivity(intent);
    }




}

