package com.wahyu.androidchallengefive.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wahyu.androidchallengefive.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOne, btnTwo;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SplashScreenActivity.MY_PREFRENCES, Context.MODE_PRIVATE);

        if (!SplashScreenActivity.syarat){
            showCustomDialog();
        }

        btnOne = findViewById(R.id.btn_number_one);
        btnTwo = findViewById(R.id.btn_number_two);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        //Mengeset judul dialog
        dialog.setTitle("Syarat dan Ketentuan");

        //Mengeset layout
        dialog.setContentView(R.layout.custom_dialog);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button btnNext = (Button) dialog.findViewById(R.id.btn_next);
        final CheckBox checkBox = dialog.findViewById(R.id.check);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getApplicationContext(), "Anda harus menyetujui dengan mencheklist " +
                            "pada checkbox yang bertuliskan saya setuju", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Menampilkan custom dialog
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_number_one:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.btn_number_two:
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                break;
        }
    }
}