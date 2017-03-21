package com.example.pellesam.outerspacemanager.CustomActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pellesam.outerspacemanager.Entity.Ship;
import com.example.pellesam.outerspacemanager.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mac14 on 07/03/2017.
 */

public class CustomAdaptaterViewShipsFleet extends ArrayAdapter<Ship> {

    private final Context context;
    private final ArrayList<Ship> ships;
    private SeekBar seekBar;
    private SharedPreferences sharedPreferences;

    public CustomAdaptaterViewShipsFleet(Context context, ArrayList<Ship> ships, SharedPreferences sharedPreferences) {
        super(context, R.layout.custom_fleet_list, ships);
        this.context = context;
        this.ships = ships;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_fleet_list, parent, false);

        TextView textViewName = (TextView) rowView.findViewById(R.id.name);
        TextView textViewNumber = (TextView) rowView.findViewById(R.id.number);
        ImageView imageViewShip = (ImageView) rowView.findViewById(R.id.imageViewShip);
        final TextView sendShip = (TextView) rowView.findViewById(R.id.sendShip);
        seekBar = (SeekBar) rowView.findViewById(R.id.seekBar);
        seekBar.setMax(ships.get(position).getAmount());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String vaisseau = "vaisseau";
                if(progress < 2) {
                    vaisseau = " vaisseau";
                }else{
                    vaisseau = " vaisseaux";
                }
                sendShip.setText("Envoyé " + String.valueOf(progress) + vaisseau);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String sendFleet = sharedPreferences.getString("fleetSend", "[0;null;0],[1;null;0],[2;null;0],[3;null;0],[4;null;0]");
                String[] fleet = sendFleet.split(",");
                fleet[position] = "["+ships.get(position).getShipId()+";"+ships.get(position).getName()+";"+seekBar.getProgress()+"]";
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < fleet.length; i++) {
                    sb.append(fleet[i]).append(",");
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fleetSend", sb.toString());
                editor.commit();
            }
        });

        textViewName.setText(ships.get(position).getName());
        textViewNumber.setText(ships.get(position).getAmount().toString());
        Glide.with(context).load("https://cdn2.nextinpact.com/images/bd/wide-linked-media/6102.jpg").centerCrop().crossFade().into(imageViewShip);


        return rowView;
    }
}
