package com.example.androidchallengeone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOne, btnTwo, btnThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.btn_one);
        btnOne.setOnClickListener(this);
        btnTwo = findViewById(R.id.btn_two);
        btnTwo.setOnClickListener(this);
        btnThree = findViewById(R.id.btn_three);
        btnThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                Intent fisrt = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(fisrt);
                break;
            case R.id.btn_two:
                Intent second = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(second);
                break;
            case R.id.btn_three:
                Intent third = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(third);
                break;
        }
    }
}