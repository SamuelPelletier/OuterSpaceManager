package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewSearches;
import com.example.pellesam.outerspacemanager.Entity.Search;
import com.example.pellesam.outerspacemanager.Entity.Searches;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac14 on 13/03/2017.
 */

public class SearchActivity extends Activity{

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = (ListView) findViewById(R.id.listView);

        SharedPreferences settings = getSharedPreferences("TOKEN", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
        final Call<Searches> request = service.getSearches(settings.getString("tokenId", "noToken"));
        request.enqueue(new Callback<Searches>() {

            @Override
            public void onResponse(Call<Searches> call, Response<Searches> response) {
                ArrayList<Search> searches = response.body().getSearches();
                listView.setAdapter(new CustomAdaptaterViewSearches(getApplicationContext(),searches));
            }

            @Override
            public void onFailure(Call<Searches> call, Throwable t) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

}
