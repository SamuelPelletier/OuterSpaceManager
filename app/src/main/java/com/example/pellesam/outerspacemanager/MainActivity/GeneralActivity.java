package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewBuildings;
import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewReports;
import com.example.pellesam.outerspacemanager.Entity.Building;
import com.example.pellesam.outerspacemanager.Entity.Report;
import com.example.pellesam.outerspacemanager.Entity.Reports;
import com.example.pellesam.outerspacemanager.Entity.User;
import com.example.pellesam.outerspacemanager.Fragment.FragmentReport;
import com.example.pellesam.outerspacemanager.Fragment.FragmentReportDetails;
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

/**
 * Created by mac14 on 13/03/2017.
 */

public class GeneralActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private TextView gas;
    private TextView gasModifier;
    private TextView minerals;
    private TextView mineralsModifier;
    private ListView listView;
    private Button attackButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view);
        gas = (TextView) findViewById(R.id.gas);
        gasModifier = (TextView) findViewById(R.id.gasModifier);
        minerals = (TextView) findViewById(R.id.minerals);
        mineralsModifier = (TextView) findViewById(R.id.mineralsModifier);
        listView = (ListView) findViewById(R.id.listView);
        attackButton = (Button) findViewById(R.id.attackReport);


        SharedPreferences settings = getSharedPreferences("TOKEN", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
        final Call<User> requestUsers = service.getUser(settings.getString("tokenId", "noToken"));
        requestUsers.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                gas.setText(response.body().getGas().toString());
                gasModifier.setText(response.body().getGasModifier().toString());
                minerals.setText(response.body().getMinerals().toString());
                mineralsModifier.setText(response.body().getMineralsModifier().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        attackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ReportAttackActivity.class);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentReport fragmentReport = (FragmentReport) getSupportFragmentManager().findFragmentById(R.id.fragmentReport);
        FragmentReportDetails fragmentReportDetails = (FragmentReportDetails)getSupportFragmentManager().findFragmentById(R.id.fragmentReportDetails);
        if(fragmentReportDetails == null|| !fragmentReportDetails.isInLayout()){
            Intent i = new Intent(getApplicationContext(),ReportDetailActivity.class);
            ArrayList<Report> reports = fragmentReport.getReports();
            Date dateReal = new Date(reports.get(position).getDate());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateFormatted = formatter.format(dateReal);
            i.putExtra("from",reports.get(position).getFrom());
            i.putExtra("to",reports.get(position).getTo());
            i.putExtra("gas",String.valueOf(reports.get(position).getGasWon()));
            i.putExtra("mineral",String.valueOf(reports.get(position).getMineralsWon()));
            i.putExtra("date",dateFormatted);
            i.putExtra("attackerFleet",String.valueOf(reports.get(position).getAttackerFleetAfterBattle().getSurvivingShips()));
            i.putExtra("defenderFleet",String.valueOf(reports.get(position).getDefenderFleetAfterBattle().getSurvivingShips()));
            startActivity(i);
        } else {
            ArrayList<Report> reports = fragmentReport.getReports();
            Date dateReal = new Date(reports.get(position).getDate());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateFormatted = formatter.format(dateReal);
            fragmentReportDetails.setText(reports.get(position).getFrom(),reports.get(position).getTo(),String.valueOf(reports.get(position).getGasWon()),String.valueOf(reports.get(position).getMineralsWon()), dateFormatted, String.valueOf(reports.get(position).getAttackerFleetAfterBattle().getSurvivingShips()), String.valueOf(reports.get(position).getDefenderFleetAfterBattle().getSurvivingShips()));
        }
    }
}
