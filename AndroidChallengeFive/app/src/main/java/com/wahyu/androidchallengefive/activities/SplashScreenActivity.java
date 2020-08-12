package com.wahyu.androidchallengefive.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.wahyu.androidchallengefive.R;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    public static final String MY_PREFRENCES = "my_prefrences";
    public static final String SESS_SYARAT = "syarat";

    public static Boolean syarat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences(MY_PREFRENCES, Context.MODE_PRIVATE);
        syarat = sharedPreferences.getBoolean(SESS_SYARAT,false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(SESS_SYARAT, true);
                editor.commit();

                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}