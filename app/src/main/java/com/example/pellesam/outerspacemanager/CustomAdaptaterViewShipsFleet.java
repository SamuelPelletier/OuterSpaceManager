package com.example.pellesam.outerspacemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
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

public class CustomAdaptaterViewShipsFleet extends ArrayAdapter<Ship>{

    private final Context context;
    private final ArrayList<Ship> ships;
    public CustomAdaptaterViewShipsFleet(Context context, ArrayList<Ship> ships) {
        super(context, R.layout.custom_fleet_list, ships);
        this.context = context;
        this.ships = ships;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_fleet_list , parent, false);

        TextView textViewName = (TextView) rowView.findViewById(R.id.name);
        TextView textViewNumber = (TextView) rowView.findViewById(R.id.number);

        textViewName.setText(ships.get(position).getName());
        textViewNumber.setText(ships.get(position).getAmount().toString());


        return rowView;
    }
}
