package com.example.kpmadan.health_ots;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class SIgnInWithPhoneActivity extends AppCompatActivity {
    private final int REQUEST_LOGIN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with_phone);

        FirebaseAuth auth =  FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            //if already login
            if(!FirebaseAuth.getInstance().getCurrentUser().getEmail().isEmpty()){
                startActivity(new Intent(this,SignInPhoneData.class)
                    .putExtra("phone",FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty())
                ) ;
                finish();

            }
            //if
            else {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(
                                        Arrays.asList(
                                                new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()

                                       )).build(),REQUEST_LOGIN);





            }
        }
    }
    @Override
    protected void onActivityResult(int requstCode,int resultCode,Intent data){
        super.onActivityResult(requstCode,resultCode,data);
        if(requstCode == REQUEST_LOGIN){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                if(!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty()){
                    startActivity(new Intent(this,SignInPhoneData.class).putExtra("Phone",FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()));
                    finish();
                    return;
                }
                else{
                    if(response==null){
                        Toast.makeText(this,"cancelled",Toast.LENGTH_SHORT).show();
                        return;

                    }
                     if(response.getErrorCode() == ErrorCodes.NO_NETWORK){
                        Toast.makeText(this,"No Internet",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR){
                        Toast.makeText(this,"Unknown Error",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        }
    }
}
