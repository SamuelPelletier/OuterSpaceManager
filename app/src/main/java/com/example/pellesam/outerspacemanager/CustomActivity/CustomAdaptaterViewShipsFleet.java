package com.example.pellesam.outerspacemanager.CustomActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pellesam.outerspacemanager.Entity.Ship;
import com.example.pellesam.outerspacemanager.R;

import java.util.ArrayList;

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
        ImageView imageViewShip = (ImageView) rowView.findViewById(R.id.imageViewShip);

        textViewName.setText(ships.get(position).getName());
        textViewNumber.setText(ships.get(position).getAmount().toString());
        Glide.with(context).load("https://cdn2.nextinpact.com/images/bd/wide-linked-media/6102.jpg").centerCrop().crossFade().into(imageViewShip);


        return rowView;
    }
}
