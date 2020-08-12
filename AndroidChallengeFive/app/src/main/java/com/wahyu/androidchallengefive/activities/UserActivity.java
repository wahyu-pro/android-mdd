package com.wahyu.androidchallengefive.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wahyu.androidchallengefive.R;

public class UserActivity extends AppCompatActivity {

    ImageView avatar;
    TextView email, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.activity_user);
        }

        fullname = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        avatar = findViewById(R.id.avatar);

        String fullName = getIntent().getStringExtra("firstName")+" "+getIntent().getStringExtra("lastName");
        String emailAd = getIntent().getStringExtra("email");
        String urlImg = getIntent().getStringExtra("avatar");

        fullname.setText(fullName);
        email.setText(emailAd);

        // load image from url
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.notfound);
        Glide.with(this).load(urlImg).apply(options).into(avatar);

    }
}