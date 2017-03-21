package com.example.pellesam.outerspacemanager.CustomActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pellesam.outerspacemanager.DAO.AttackDataSource;
import com.example.pellesam.outerspacemanager.Entity.Attack;
import com.example.pellesam.outerspacemanager.Entity.HttpResponse;
import com.example.pellesam.outerspacemanager.Entity.Report;
import com.example.pellesam.outerspacemanager.Entity.Ship;
import com.example.pellesam.outerspacemanager.Entity.Ships;
import com.example.pellesam.outerspacemanager.MainActivity.MainActivity;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac14 on 13/03/2017.
 */

public class CustomAdaptaterViewReportAttacks extends ArrayAdapter<Attack>{

    private final Context context;
    private ArrayList<Attack> attacks;
    public CustomAdaptaterViewReportAttacks(Context context, ArrayList<Attack> attacks) {
        super(context, R.layout.custom_attack_report_list, attacks);
        this.context = context;
        this.attacks = attacks;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_attack_report_list, parent, false);

        TextView to = (TextView) rowView.findViewById(R.id.to);
        TextView percent = (TextView) rowView.findViewById(R.id.percent);
        TextView fleet = (TextView) rowView.findViewById(R.id.fleet);

        Gson gson = new Gson();
        Ships ships = gson.fromJson(attacks.get(position).getFleetSend(), Ships.class);

        String fleetString = "";
        for(int i =0; i<ships.getSize(); i++) {
            if(ships.getShips().get(i).getAmount() != 0) {
                fleetString += "\n" + ships.getShips().get(i).getName() + " : " + ships.getShips().get(i).getAmount();
            }
        }
        fleet.setText(fleetString);
        final ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.progressBar);

        to.setText("Vous avez attaqué: "+attacks.get(position).getUsername());
        final Integer progress = Integer.parseInt(String.valueOf(((System.currentTimeMillis()-attacks.get(position).getEnd()))/((attacks.get(position).getEnd()-attacks.get(position).getBegin()))*100));
        progressBar.setProgress(progress);
        percent.setText(progress+"%");


        return rowView;
    }




}