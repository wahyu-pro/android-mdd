package com.example.androidchallengethree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_reg, btn_song, btn_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_reg = findViewById(R.id.btn_register);
        btn_song = findViewById(R.id.btn_song);
        btn_cart = findViewById(R.id.btn_cart);

        btn_reg.setOnClickListener(this);
        btn_song.setOnClickListener(this);
        btn_cart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                startActivity(new Intent(MainActivity.this, RegistrasiActivity.class));
                break;
            case R.id.btn_song:
                startActivity(new Intent(MainActivity.this, MusicPlayerActivity.class));
                break;
            case R.id.btn_cart:
                startActivity(new Intent(MainActivity.this, ListBelanjaActivity.class));
                break;
        }
    }
}