package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewAttack;
import com.example.pellesam.outerspacemanager.Entity.Ships;
import com.example.pellesam.outerspacemanager.Entity.User;
import com.example.pellesam.outerspacemanager.Entity.Users;
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

public class AttackActivity extends Activity {

    private ListView listView;
    private Ships ships;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);
        listView = (ListView) findViewById(R.id.listView);
        ships = (Ships) getIntent().getSerializableExtra("fleet");

        SharedPreferences settings = getSharedPreferences("TOKEN", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
        final Call<Users> request = service.getUsers(settings.getString("tokenId", "noToken"),0,20);
        request.enqueue(new Callback<Users>() {

            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                ArrayList<User> users = response.body().getUsers();
                listView.setAdapter(new CustomAdaptaterViewAttack(getApplicationContext(),users, ships, getSharedPreferences("TOKEN", 0)));
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}

