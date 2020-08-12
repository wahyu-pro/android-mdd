package com.wahyu.androidchallengefive.services;

import com.wahyu.androidchallengefive.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("posts")
    Call<List<PostModel>> getPost();
}
