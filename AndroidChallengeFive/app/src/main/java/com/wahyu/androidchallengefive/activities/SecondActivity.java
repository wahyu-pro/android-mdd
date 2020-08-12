package com.wahyu.androidchallengefive.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.wahyu.androidchallengefive.R;
import com.wahyu.androidchallengefive.adapters.UserAdapter;
import com.wahyu.androidchallengefive.clients.ApiClient;
import com.wahyu.androidchallengefive.models.DataItem;
import com.wahyu.androidchallengefive.models.UsersModel;
import com.wahyu.androidchallengefive.services.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private SearchView searchView;
    UserService service;
    private UserAdapter adapter;
    private RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.activity_second);
        }

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new UserAdapter();

        getListUsers();
    }

    private void getListUsers(){
        service = ApiClient.client(UserService.class, "https://reqres.in/api/");
        service.getUser().enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                if (response.isSuccessful() && response.body() != null){
                    generateData(response.body().getData());
                    showLoading(false);
                }
            }

            @Override
            public void onFailure(Call<UsersModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void generateData(List<DataItem> itemList) {
        adapter.setUserList(getApplicationContext(), itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickCallback(this::showSelectedUser);
        showLoading(false);
    }

    private void showSelectedUser(DataItem data) {
        Bundle bundle = new Bundle();
        bundle.putString("email", data.getEmail());
        bundle.putString("avatar", data.getAvatar());
        bundle.putString("firstName", data.getFirstName());
        bundle.putString("lastName", data.getLastName());
        Intent i = new Intent(getApplicationContext(), UserActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}