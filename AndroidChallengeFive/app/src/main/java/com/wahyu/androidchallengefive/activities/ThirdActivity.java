package com.wahyu.androidchallengefive.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wahyu.androidchallengefive.R;
import com.wahyu.androidchallengefive.adapters.PostAdapter;
import com.wahyu.androidchallengefive.clients.ApiClient;
import com.wahyu.androidchallengefive.models.DataItem;
import com.wahyu.androidchallengefive.models.PostModel;
import com.wahyu.androidchallengefive.services.PostService;
import com.wahyu.androidchallengefive.viewmodels.PostViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    PostAdapter adapter;
    PostViewModel viewModel;
    RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.activity_third);
        }

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PostAdapter();
        viewModel = new ViewModelProvider(ThirdActivity.this, new ViewModelProvider.NewInstanceFactory()).get(PostViewModel.class);
        viewModel.setListPosts();
        getUser();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickCallback(this::showSelectedUser);

    }

    private void showSelectedUser(PostModel data) {
        Bundle bundle = new Bundle();
        Intent i = new Intent(getApplicationContext(), PostActivity.class);
        i.putExtras(bundle);
        startActivity(i);
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

    private void getUser(){
        viewModel.getListPosts().observe(ThirdActivity.this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                if (postModels != null){
                    adapter.setPostList(ThirdActivity.this, postModels);
                }
            }
        });
    }
}