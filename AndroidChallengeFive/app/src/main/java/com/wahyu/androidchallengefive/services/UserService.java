package com.wahyu.androidchallengefive.services;

import com.wahyu.androidchallengefive.models.DataItem;
import com.wahyu.androidchallengefive.models.UsersModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("users")
    Call<UsersModel> getUser();
}
