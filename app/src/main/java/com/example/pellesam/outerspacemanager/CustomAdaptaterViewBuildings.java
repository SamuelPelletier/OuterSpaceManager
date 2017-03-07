package com.example.pellesam.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mac14 on 07/03/2017.
 */

public class CustomAdaptaterViewBuildings extends ArrayAdapter<Building>{

    private final Context context;
    private final ArrayList<Building> buildings;
    public CustomAdaptaterViewBuildings(Context context, ArrayList<Building> buildings) {
        super(context, R.layout.custom_building_list, buildings);
        this.context = context;
        this.buildings = buildings;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_building_list, parent, false);

        TextView textViewName = (TextView) rowView.findViewById(R.id.name);
        TextView textViewEffect = (TextView) rowView.findViewById(R.id.effect);
        TextView textViewGasCost = (TextView) rowView.findViewById(R.id.gasCost);
        TextView textViewMineralCost = (TextView) rowView.findViewById(R.id.mineralCost);
        TextView textViewTimeToBuild = (TextView) rowView.findViewById(R.id.timeToBuild);
        TextView textViewLevel = (TextView) rowView.findViewById(R.id.level);
        TextView textViewBuild = (TextView) rowView.findViewById(R.id.build);

        textViewName.setText("Nom : "+buildings.get(position).getName());
        textViewEffect.setText("Effet : "+buildings.get(position).getAmountEffect());
        textViewGasCost.setText("Cout en gas : "+buildings.get(position).getGasCost());
        textViewMineralCost.setText("Cout en minéraux : "+buildings.get(position).getMineralCost());
        textViewTimeToBuild.setText("Temps de construction : "+buildings.get(position).getTimeToBuild());
        if(buildings.get(position).isBuilding()) {
            textViewBuild.setText("Améliorer");
            textViewLevel.setText(buildings.get(position).getLevel().toString());
        }else{
            textViewBuild.setText("Construire");
            textViewLevel.setVisibility(View.GONE);
        }

        return rowView;
    }

}
