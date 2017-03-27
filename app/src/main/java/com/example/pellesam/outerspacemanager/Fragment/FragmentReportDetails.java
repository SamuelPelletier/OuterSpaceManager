package com.example.pellesam.outerspacemanager.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewReportAttacks;
import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewReports;
import com.example.pellesam.outerspacemanager.DAO.AttackDataSource;
import com.example.pellesam.outerspacemanager.Entity.Attack;
import com.example.pellesam.outerspacemanager.Entity.Report;
import com.example.pellesam.outerspacemanager.Entity.Reports;
import com.example.pellesam.outerspacemanager.MainActivity.GeneralActivity;
import com.example.pellesam.outerspacemanager.MainActivity.MainActivity;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Math.toIntExact;

/**
 * Created by mac14 on 27/03/2017.
 */

public class FragmentReportDetails extends Fragment {
    private TextView from;
    private TextView to;
    private TextView gasWon;
    private TextView mineralsWon;
    private TextView date;
    private TextView attackerFleetAfterBattle;
    private TextView defenderFleetAfterBattle;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report_details,container);
         from = (TextView) v.findViewById(R.id.from);
         to = (TextView) v.findViewById(R.id.to);
         gasWon = (TextView) v.findViewById(R.id.gas);
         mineralsWon = (TextView) v.findViewById(R.id.mineral);
         date = (TextView) v.findViewById(R.id.date);
         attackerFleetAfterBattle = (TextView) v.findViewById(R.id.attackerFleet);
         defenderFleetAfterBattle = (TextView) v.findViewById(R.id.defenderFleet);
        return v;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void setText(String fromString, String toString,String gasString,String mineralString,String dateString,String attackerFleetAfterBattleString,String defenderFleetAfterBattleString){
        from.setText("Attaquant: "+fromString);
        to.setText("Défenseur: "+toString);
        gasWon.setText("Gas volé: "+gasString);
        mineralsWon.setText("Mineral volé: "+mineralString);
        date.setText("Date de l'attaque: "+dateString);
        attackerFleetAfterBattle.setText("L'attaquant dispose d'encore: "+attackerFleetAfterBattleString+" vaisseau(x)");
        defenderFleetAfterBattle.setText("Le défenseur dispose d'encore: "+defenderFleetAfterBattleString+" vaisseau(x)");
    }
}
