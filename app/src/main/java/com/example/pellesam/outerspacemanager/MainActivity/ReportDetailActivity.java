package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pellesam.outerspacemanager.CustomActivity.CustomAdaptaterViewUsers;
import com.example.pellesam.outerspacemanager.Entity.User;
import com.example.pellesam.outerspacemanager.Entity.Users;
import com.example.pellesam.outerspacemanager.Fragment.FragmentReportDetails;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac14 on 27/03/2017.
 */

public class ReportDetailActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        String from  = (String) getIntent().getExtras().get("from");
        String to  = (String) getIntent().getExtras().get("to");
        String gas  = (String) getIntent().getExtras().get("gas");
        String mineral  = (String) getIntent().getExtras().get("mineral");
        String date  = (String) getIntent().getExtras().get("date");
        String attackerFleet  = (String) getIntent().getExtras().get("attackerFleet");
        String defenderFleet  = (String) getIntent().getExtras().get("defenderFleet");

        FragmentReportDetails fragmentReportDetails = (FragmentReportDetails)getSupportFragmentManager().findFragmentById(R.id.fragmentReportDetails);
        fragmentReportDetails.setText(from,to,gas, mineral, date, attackerFleet, defenderFleet);
    }
}
