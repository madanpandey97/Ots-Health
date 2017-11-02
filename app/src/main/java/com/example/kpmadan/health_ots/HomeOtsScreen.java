package com.example.kpmadan.health_ots;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.tag;

public class HomeOtsScreen extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, DashboardFragment.OnFragmentInteractionListener, HelpFragment.OnFragmentInteractionListener {
    private DatabaseReference mDatabaseUser;
    private FirebaseAuth mAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Ots-Health");
                    HomeFragment hf = new HomeFragment();
                    FragmentTransaction ftn = getSupportFragmentManager().beginTransaction();
                    ftn.replace(R.id.frame_design, hf, "Ots-Health");
                    ftn.commit();
                    return true;
                case R.id.navigation_dashboard:
                    setTitle("Ots-Health");
                    DashboardFragment df = new DashboardFragment();
                    FragmentTransaction dfn = getSupportFragmentManager().beginTransaction();
                    dfn.replace(R.id.frame_design, df, "Ots-Health");
                    dfn.commit();
                    return true;
                case R.id.navigation_notifications:
                    setTitle("Ots-Health");
                    HomeFragment hft = new HomeFragment();
                    FragmentTransaction ftnt = getSupportFragmentManager().beginTransaction();
                    ftnt.replace(R.id.frame_design, hft, "Ots-Health");
                    ftnt.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ots_screen);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setTitle("Ots-Health");
        HomeFragment hf = new HomeFragment();
        FragmentTransaction ftn = getSupportFragmentManager().beginTransaction();
        ftn.replace(R.id.frame_design, hf, "Ots-Health");
        ftn.commit();

        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabaseUser.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart(){
        super.onStart();

    }

    public  void onFragmentInteraction(Uri uri){

    }
    private void checkUserExist(){
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(user_id)){
                    Intent mainIntent = new Intent(HomeOtsScreen.this,UserLoginInfoActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
