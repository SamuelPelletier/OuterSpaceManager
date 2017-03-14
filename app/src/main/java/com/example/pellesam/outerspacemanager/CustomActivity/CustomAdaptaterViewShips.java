package com.example.pellesam.outerspacemanager.CustomActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pellesam.outerspacemanager.Entity.Amount;
import com.example.pellesam.outerspacemanager.Entity.Ship;
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

public class CustomAdaptaterViewShips extends ArrayAdapter<Ship> implements View.OnClickListener {

    private final Context context;
    private final ArrayList<Ship> ships;

    public CustomAdaptaterViewShips(Context context, ArrayList<Ship> ships) {
        super(context, R.layout.custom_ship_list, ships);
        this.context = context;
        this.ships = ships;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_ship_list, parent, false);

        TextView textViewName = (TextView) rowView.findViewById(R.id.name);
        TextView textViewGasCost = (TextView) rowView.findViewById(R.id.gasCost);
        TextView textViewMineralCost = (TextView) rowView.findViewById(R.id.mineralCost);
        TextView textViewTimeToBuild = (TextView) rowView.findViewById(R.id.timeToBuild);
        final EditText editTextNumber = (EditText) rowView.findViewById(R.id.number);
        final Button buttonBuild = (Button) rowView.findViewById(R.id.build);
        ImageView imageViewShip = (ImageView) rowView.findViewById(R.id.imageViewShip);

        textViewName.setText(ships.get(position).getName());
        textViewGasCost.setText("Cout en gas : " + ships.get(position).getGasCost());
        textViewMineralCost.setText("Cout en minéraux : " + ships.get(position).getMineralCost());
        textViewTimeToBuild.setText("Temps de construction : " + ships.get(position).getTimeToBuild());
        editTextNumber.setTextColor(Color.rgb(255,140,0));
        buttonBuild.setText("Créer");
        Glide.with(context).load("https://cdn2.nextinpact.com/images/bd/wide-linked-media/6102.jpg").centerCrop().crossFade().into(imageViewShip);


        buttonBuild.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences settings = context.getSharedPreferences("TOKEN", 0);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
                Integer numberTemp = 0;
                try {
                    numberTemp = Integer.valueOf(editTextNumber.getText().toString());
                } catch (Exception e) {
                }
                final Integer number = numberTemp;

                if(number != 0) {
                    final Call<Ship> request = service.createShip(settings.getString("tokenId", "noToken"), position, new Amount(number));
                    request.enqueue(new Callback<Ship>() {

                        @Override
                        public void onResponse(Call<Ship> call, Response<Ship> response) {
                            CharSequence text = "Echec de la création";
                            if (response.code() == 200) {
                                text = number + " unité créé avec succés";
                            }
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }

                        @Override
                        public void onFailure(Call<Ship> call, Throwable t) {
                            CharSequence text = "Echec de la création";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    });
                }
            }
        });

        return rowView;
    }

    @Override
    public void onClick(View v) {

    }
}
