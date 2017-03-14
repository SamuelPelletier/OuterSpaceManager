package com.example.pellesam.outerspacemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac14 on 07/03/2017.
 */

public class FleetActivity extends Activity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);
        listView = (ListView) findViewById(R.id.listView);

        SharedPreferences settings = getSharedPreferences("TOKEN", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
        final Call<Ships> request = service.getFleet(settings.getString("tokenId", "noToken"));
        request.enqueue(new Callback<Ships>() {

            @Override
            public void onResponse(Call<Ships> call, Response<Ships> response) {
                ArrayList<Ship> ships = response.body().getShips();
                if(response.body().getNumberOfShip() > 0) {
                    listView.setAdapter(new CustomAdaptaterViewShipsFleet(getApplicationContext(), ships));
                }else{
                    CharSequence text = "Vous n'avez pas de vaisseau :(";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(myIntent);
                }
            }

            @Override
            public void onFailure(Call<Ships> call, Throwable t) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}

