package com.wahyu.androidchallengefive.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import com.wahyu.androidchallengefive.R;
import com.wahyu.androidchallengefive.databinding.ActivityPostBinding;
import com.wahyu.androidchallengefive.models.PostModel;

public class PostActivity extends AppCompatActivity {

    public static final String EXTRA_POST = "extra_post";

    ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);
        final PostModel post =  getIntent().getParcelableExtra(EXTRA_POST);

        binding.setPost(post);

    }
}