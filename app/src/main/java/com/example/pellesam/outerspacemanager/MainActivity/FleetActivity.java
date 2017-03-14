package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewShipsFleet;
import com.example.pellesam.outerspacemanager.Entity.Amount;
import com.example.pellesam.outerspacemanager.Entity.Ship;
import com.example.pellesam.outerspacemanager.Entity.Ships;
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

public class FleetActivity extends Activity implements View.OnClickListener{

    private ListView listView;
    private Intent myIntentAttack;
    private Button buttonFullAttack;
    private Button buttonLimitedAttack;
    private Button buttonProbe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);
        listView = (ListView) findViewById(R.id.listView);
        buttonFullAttack = (Button) findViewById(R.id.buttonFullAttack);
        buttonLimitedAttack = (Button) findViewById(R.id.buttonLimitedAttack);
        buttonProbe = (Button) findViewById(R.id.buttonProbe);
        myIntentAttack = new Intent(getApplicationContext(), AttackActivity.class);

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
                    myIntentAttack.putExtra("fleet", response.body());
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

        buttonFullAttack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(myIntentAttack);
            }
        });

        buttonLimitedAttack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        buttonProbe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}

