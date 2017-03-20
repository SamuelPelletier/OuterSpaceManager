package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
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
    private boolean haveProbe = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);
        listView = (ListView) findViewById(R.id.listView);
        buttonFullAttack = (Button) findViewById(R.id.buttonFullAttack);
        buttonLimitedAttack = (Button) findViewById(R.id.buttonLimitedAttack);
        buttonProbe = (Button) findViewById(R.id.buttonProbe);
        myIntentAttack = new Intent(getApplicationContext(), AttackActivity.class);

        final SharedPreferences settings = getSharedPreferences("TOKEN", 0);

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
                    final Integer amountProbe = response.body().getShips().get(2).getAmount();
                    listView.setAdapter(new CustomAdaptaterViewShipsFleet(getApplicationContext(), ships, settings));
                    myIntentAttack.putExtra("fleet", response.body());

                        ArrayList<Ship> arrayShip = new ArrayList<Ship>();
                        arrayShip.add(new Ship(2,1));
                        final Ships shipsProbe = new Ships(arrayShip);
                        buttonProbe.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v ) {
                                if(amountProbe > 0) {
                                myIntentAttack.putExtra("fleet",shipsProbe);
                                startActivity(myIntentAttack);
                                }else{
                                    CharSequence text = "Vous n'avez pas de sonde :(";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                                    toast.show();
                                }
                            }
                        });
                    buttonFullAttack.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            startActivity(myIntentAttack);
                        }
                    });
                    buttonLimitedAttack.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            String sendFleet = settings.getString("fleetSend", "[0;0],[1;0],[2;0],[3;0],[4;0]");
                            String[] fleet = sendFleet.split(",");
                            ArrayList<Ship> ships =  new ArrayList<Ship>();
                            for(int i=0;i<fleet.length; i++ ){
                                Integer id = Integer.valueOf(String.valueOf(fleet[i].charAt(1)));
                                Integer amount = Integer.valueOf(String.valueOf(fleet[i].charAt(3)));
                                ships.add(new Ship(id, amount));
                            }
                            myIntentAttack.putExtra("fleet", new Ships(ships));
                            startActivity(myIntentAttack);
                        }
                    });
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

    @Override
    public void onClick(View v) {

    }
}

