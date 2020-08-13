package com.wahyu.challengefourmaterialdesign.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wahyu.challengefourmaterialdesign.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToNumberOne(View view) {
        startActivity(new Intent(getApplicationContext(), FirstActivity.class));
    }

    public void moveToNumberTwo(View view) {
        startActivity(new Intent(getApplicationContext(), SecondActivity.class));
    }

    public void moveToNumberThree(View view) {
        startActivity(new Intent(getApplicationContext(), ThirdActivity.class));
    }

    public void moveToNumberFour(View view) {
        startActivity(new Intent(getApplicationContext(), ActivityFour.class));
    }
}