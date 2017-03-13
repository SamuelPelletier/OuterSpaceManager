package com.example.pellesam.outerspacemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
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

public class CustomAdaptaterViewShips extends ArrayAdapter<Ship> implements View.OnClickListener{

    private final Context context;
    private final ArrayList<Ship> ships;
    public CustomAdaptaterViewShips(Context context, ArrayList<Ship> ships) {
        super(context, R.layout.custom_building_list, ships);
        this.context = context;
        this.ships = ships;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_building_list, parent, false);

        TextView textViewName = (TextView) rowView.findViewById(R.id.name);
        TextView textViewEffect = (TextView) rowView.findViewById(R.id.effect);
        TextView textViewGasCost = (TextView) rowView.findViewById(R.id.gasCost);
        TextView textViewMineralCost = (TextView) rowView.findViewById(R.id.mineralCost);
        TextView textViewTimeToBuild = (TextView) rowView.findViewById(R.id.timeToBuild);
        TextView textViewLevel = (TextView) rowView.findViewById(R.id.level);
        final Button buttonBuild = (Button) rowView.findViewById(R.id.build);

        textViewName.setText(ships.get(position).getName());
        textViewEffect.setText("Effet : "+ships.get(position).getAmountEffect());
        textViewGasCost.setText("Cout en gas : "+ships.get(position).getGasCost());
        textViewMineralCost.setText("Cout en minéraux : "+ships.get(position).getMineralCost());
        textViewTimeToBuild.setText("Temps de construction : "+ships.get(position).getTimeToBuild());
        if(ships.get(position).isBuilding() || ships.get(position).getLevel() > 1) {
            buttonBuild.setText("Améliorer");
            textViewLevel.setText(ships.get(position).getLevel().toString());
        }else{
            buttonBuild.setText("Construire");
            textViewLevel.setVisibility(View.GONE);
        }

        buttonBuild.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences settings = context.getSharedPreferences("TOKEN", 0);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
                final Call<Building> request = service.constructBuilding(settings.getString("tokenId", "noToken"), position);
                request.enqueue(new Callback<Building>() {

                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        CharSequence text = "Echec de l'amélioration";
                        if(response.code() == 200) {
                            text = "Batiment améliorer avec succés";
                        }
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {
                        CharSequence text = "Echec de l'amélioration";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
            }
        });

        return rowView;
    }

    @Override
    public void onClick(View v) {

    }
}
