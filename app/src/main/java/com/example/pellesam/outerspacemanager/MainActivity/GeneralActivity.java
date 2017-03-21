package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewBuildings;
import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewReports;
import com.example.pellesam.outerspacemanager.Entity.Building;
import com.example.pellesam.outerspacemanager.Entity.Report;
import com.example.pellesam.outerspacemanager.Entity.Reports;
import com.example.pellesam.outerspacemanager.Entity.User;
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

public class GeneralActivity extends Activity implements View.OnClickListener{

    private TextView gas;
    private TextView gasModifier;
    private TextView minerals;
    private TextView mineralsModifier;
    private ListView listView;
    private Button attackButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view);
        gas = (TextView) findViewById(R.id.gas);
        gasModifier = (TextView) findViewById(R.id.gasModifier);
        minerals = (TextView) findViewById(R.id.minerals);
        mineralsModifier = (TextView) findViewById(R.id.mineralsModifier);
        listView = (ListView) findViewById(R.id.listView);
        attackButton = (Button) findViewById(R.id.attackReport);


        SharedPreferences settings = getSharedPreferences("TOKEN", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
        final Call<User> requestUsers = service.getUser(settings.getString("tokenId", "noToken"));
        requestUsers.enqueue(new Callback<User>() {

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

        final Call<Reports> requestReports = service.getReports(settings.getString("tokenId", "noToken"),0,4);
        requestReports.enqueue(new Callback<Reports>() {

            @Override
            public void onResponse(Call<Reports> call, Response<Reports> response) {
                if(response.isSuccessful()) {
                    ArrayList<Report> reports = response.body().getReports();
                    listView.setAdapter(new CustomAdaptaterViewReports(getApplicationContext(), reports));
                }
            }

            @Override
            public void onFailure(Call<Reports> call, Throwable t) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        attackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ReportAttackActivity.class);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
