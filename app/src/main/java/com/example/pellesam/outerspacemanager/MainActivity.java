package com.example.pellesam.outerspacemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac14 on 07/03/2017.
 */

public class MainActivity extends Activity implements View.OnClickListener{

    private Button generalView;
    private Button building;
    private Button boat;
    private Button search;
    private Button spatialSite;
    private Button galaxy;
    private Button disconnect;
    private TextView username;
    private TextView point;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generalView = (Button) findViewById(R.id.generalView);
        building = (Button) findViewById(R.id.Building);
        boat = (Button) findViewById(R.id.Boat);
        search = (Button) findViewById(R.id.Search);
        spatialSite = (Button) findViewById(R.id.spatialSite);
        galaxy = (Button) findViewById(R.id.galaxy);
        disconnect = (Button) findViewById(R.id.disconnect);
        username = (TextView) findViewById(R.id.username);
        point = (TextView) findViewById(R.id.point);

        SharedPreferences settings = getSharedPreferences("TOKEN", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
        final Call<User> request = service.getUser(settings.getString("tokenId", "noToken"));
        request.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                username.setText(response.body().getUsername());
                point.setText("Points: "+response.body().getPoints());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Intent myIntent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(myIntent);
            }
        });

        generalView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), GeneralActivity.class);
                startActivity(myIntent);
            }
        });

        building.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), BuildingActivity.class);
                startActivity(myIntent);
            }
        });

        boat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(myIntent);
            }
        });

        spatialSite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ShipActivity.class);
                startActivity(myIntent);
            }
        });

        galaxy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), GalaxyActivity.class);
                startActivity(myIntent);
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("TOKEN", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("tokenId", "noToken");
                editor.commit();
                Intent myIntent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
