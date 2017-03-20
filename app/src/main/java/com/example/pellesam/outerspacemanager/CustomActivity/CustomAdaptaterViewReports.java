package com.example.pellesam.outerspacemanager.CustomActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pellesam.outerspacemanager.Entity.Report;
import com.example.pellesam.outerspacemanager.Entity.Ship;
import com.example.pellesam.outerspacemanager.Entity.User;
import com.example.pellesam.outerspacemanager.R;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mac14 on 13/03/2017.
 */

public class CustomAdaptaterViewReports extends ArrayAdapter<Report> {

    private final Context context;
    private final ArrayList<Report> reports;
    public CustomAdaptaterViewReports(Context context, ArrayList<Report> reports) {
        super(context, R.layout.custom_report_list, reports);
        this.context = context;
        this.reports = reports;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_report_list, parent, false);

        TextView to = (TextView) rowView.findViewById(R.id.to);
        TextView from = (TextView) rowView.findViewById(R.id.from);
        TextView gasWon = (TextView) rowView.findViewById(R.id.gasWon);
        TextView mineralsWon = (TextView) rowView.findViewById(R.id.mineralsWon);
        TextView date = (TextView) rowView.findViewById(R.id.date);
        TextView attackerFleetAfterBattle = (TextView) rowView.findViewById(R.id.attackerFleetAfterBattle);
        TextView defenderFleetAfterBattle = (TextView) rowView.findViewById(R.id.defenderFleetAfterBattle);

        from.setText("Attaquant: "+reports.get(position).getFrom());
        to.setText("Défenseur: "+reports.get(position).getTo());
        gasWon.setText("Gas volé: "+String.valueOf(reports.get(position).getGasWon()));
        mineralsWon.setText("Mineral volé: "+String.valueOf(reports.get(position).getMineralsWon()));
        Date dateReal = new Date(reports.get(position).getDate());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateFormatted = formatter.format(dateReal);
        date.setText("Date de l'attaque: "+dateFormatted);
        attackerFleetAfterBattle.setText("Vous avez perdu: "+String.valueOf(reports.get(position).getAttackerFleetAfterBattle().getSurvivingShips())+" vaisseau(x)");
        defenderFleetAfterBattle.setText("Il lui reste: "+String.valueOf(reports.get(position).getDefenderFleetAfterBattle().getSurvivingShips())+" vaisseau(x)");

        return rowView;
    }

}