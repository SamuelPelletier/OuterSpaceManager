package com.example.pellesam.outerspacemanager.CustomActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pellesam.outerspacemanager.Entity.Amount;
import com.example.pellesam.outerspacemanager.Entity.Building;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;

/**
 * Created by mac14 on 07/03/2017.
 */

public class CustomAdaptaterViewBuildings extends ArrayAdapter<Building> implements View.OnClickListener{

    private final Context context;
    private final ArrayList<Building> buildings;
    public CustomAdaptaterViewBuildings(Context context, ArrayList<Building> buildings) {
        super(context, R.layout.custom_building_list, buildings);
        this.context = context;
        this.buildings = buildings;
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

        textViewName.setText(buildings.get(position).getName());
        textViewEffect.setText("Effet : "+buildings.get(position).getAmountEffect());
        textViewGasCost.setText("Cout en gas : "+buildings.get(position).getGasCost());
        textViewMineralCost.setText("Cout en minéraux : "+buildings.get(position).getMineralCost());
        textViewTimeToBuild.setText("Temps de construction : "+buildings.get(position).getTimeToBuild());
        if(buildings.get(position).getLevel() != null && buildings.get(position).getLevel().equals(1)) {
            buttonBuild.setText("Améliorer");
            textViewLevel.setText(buildings.get(position).getLevel().toString());
        }else{
            buttonBuild.setText("Construire");
            textViewLevel.setVisibility(GONE);
        }

        if (buildings.get(position).isBuilding()){
            LinearLayout buildingLayout = (LinearLayout) rowView.findViewById(R.id.buildingLayout);
            Integer orange = Color.rgb(255,140,0);
            buildingLayout.setBackgroundColor(orange);
            buttonBuild.setVisibility(GONE);
            textViewLevel.setVisibility(View.VISIBLE);
            if(buildings.get(position).getLevel() != null) {
                textViewLevel.setText("En amélioration vers le level " + (buildings.get(position).getLevel() + 1));
            }else{
                textViewLevel.setText("En amélioration vers le level 1");
            }
            textViewLevel.setTextColor(orange);
        }

        buttonBuild.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences settings = context.getSharedPreferences("TOKEN", 0);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
                final Call<Building> request = service.constructBuilding(settings.getString("tokenId", "noToken"), buildings.get(position).getBuildingId());
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
