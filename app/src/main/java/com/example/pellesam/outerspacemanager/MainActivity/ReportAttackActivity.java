package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewReportAttacks;
import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewReports;
import com.example.pellesam.outerspacemanager.DAO.AttackDataSource;
import com.example.pellesam.outerspacemanager.Entity.Attack;
import com.example.pellesam.outerspacemanager.Entity.Report;
import com.example.pellesam.outerspacemanager.Entity.Reports;
import com.example.pellesam.outerspacemanager.Entity.User;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Math.toIntExact;

/**
 * Created by mac14 on 13/03/2017.
 */

public class ReportAttackActivity extends Activity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack_report);
        AttackDataSource attackDataSource = new AttackDataSource(getApplicationContext());
        attackDataSource.open();
        listView = (ListView) findViewById(R.id.listView);
        ArrayList<Attack> attacks = attackDataSource.getAllAttacks();
        for(int i = 0; i< attacks.size(); i++) {
            long dureeAvantFin = (attacks.get(i).getEnd()-System.currentTimeMillis());
            long dureeTotal  = (attacks.get(i).getEnd()-attacks.get(i).getBegin());
            double dureeRestante = Double.valueOf(dureeAvantFin) / Double.valueOf(dureeTotal);
            Integer progress = 100-toIntExact(Math.round(dureeRestante*100));
            if(progress >= 100) {
                attackDataSource.deleteAttack(attacks.get(i));
            }
        }
        attacks = attackDataSource.getAllAttacks();
        attackDataSource.close();
        listView.setAdapter(new CustomAdaptaterViewReportAttacks(getApplicationContext(), attacks));
    }


}
