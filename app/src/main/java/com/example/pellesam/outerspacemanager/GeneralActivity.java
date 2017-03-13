package com.example.pellesam.outerspacemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac14 on 13/03/2017.
 */

public class GeneralActivity extends Activity {

    private TextView gas;
    private TextView gasModifier;
    private TextView minerals;
    private TextView mineralsModifier;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view);
        gas = (TextView) findViewById(R.id.gas);
        gasModifier = (TextView) findViewById(R.id.gasModifier);
        minerals = (TextView) findViewById(R.id.minerals);
        mineralsModifier = (TextView) findViewById(R.id.mineralsModifier);

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
                gas.setText(response.body().getGas().toString());
                gasModifier.setText(response.body().getGasModifier().toString());
                minerals.setText(response.body().getMinerals().toString());
                mineralsModifier.setText(response.body().getMineralsModifier().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
