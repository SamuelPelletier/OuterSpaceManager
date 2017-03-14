package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewBuildings;
import com.example.pellesam.outerspacemanager.Entity.Amount;
import com.example.pellesam.outerspacemanager.Entity.Building;
import com.example.pellesam.outerspacemanager.Entity.Buildings;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac14 on 07/03/2017.
 */

public class BuildingActivity extends Activity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        listView = (ListView) findViewById(R.id.listView);

        SharedPreferences settings = getSharedPreferences("TOKEN", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
        final Call<Buildings> request = service.getBuilding(settings.getString("tokenId", "noToken"));
        request.enqueue(new Callback<Buildings>() {

            @Override
            public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                ArrayList<Building> buildings = response.body().getBuildings();
                listView.setAdapter(new CustomAdaptaterViewBuildings(getApplicationContext(),buildings));
            }

            @Override
            public void onFailure(Call<Buildings> call, Throwable t) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}

