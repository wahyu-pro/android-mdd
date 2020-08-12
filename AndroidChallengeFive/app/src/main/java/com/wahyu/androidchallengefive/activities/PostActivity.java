package com.wahyu.androidchallengefive.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.wahyu.androidchallengefive.R;
import com.wahyu.androidchallengefive.models.PostModel;

public class PostActivity extends AppCompatActivity {

    public static final String EXTRA_POST = "extra_post";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final PostModel post =  getIntent().getParcelableExtra(EXTRA_POST);

        TextView header = findViewById(R.id.header);
        header.setText(post.getTitle());
        TextView body = findViewById(R.id.body);
        body.setText(post.getBody());

    }
}