package com.example.dishycloud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dishycloud.R;
import com.example.dishycloud.sqlites.DatabaseHelper;


public class SplashActivity extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mDatabaseHelper = new DatabaseHelper(this);
        String token = mDatabaseHelper.getToken();

        if (!token.equals("")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent =  new Intent(SplashActivity.this,HomeActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            },1500);
        }else if (token.equals("")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent =  new Intent(SplashActivity.this,SignInActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            },1500);
        }

    }
}
