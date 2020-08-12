package com.wahyu.androidchallengefive.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wahyu.androidchallengefive.clients.ApiClient;
import com.wahyu.androidchallengefive.models.PostModel;
import com.wahyu.androidchallengefive.services.PostService;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    MutableLiveData<List<PostModel>> listPosts = new MutableLiveData<>();

    public LiveData<List<PostModel>> getListPosts() {
        return listPosts;
    }

    public void setListPosts() {
        ApiClient.client(PostService.class, "http://jsonplaceholder.typicode.com/")
        .getPost().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                listPosts.postValue(Objects.requireNonNull(response).body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
