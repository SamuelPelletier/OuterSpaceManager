package com.example.pellesam.outerspacemanager.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Math.toIntExact;

/**
 * Created by mac14 on 27/03/2017.
 */

public class FragmentReport extends Fragment {
    private ListView listView;
    private ArrayList<Report> reports;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reports,container);
        listView = (ListView)v.findViewById(R.id.listView);
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setAdapter(new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1));
        listView.setOnItemClickListener((GeneralActivity)getActivity());

        SharedPreferences settings = getActivity().getSharedPreferences("TOKEN", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);

        final Call<Reports> requestReports = service.getReports(settings.getString("tokenId", "noToken"),0,4);
        requestReports.enqueue(new Callback<Reports>() {

            @Override
            public void onResponse(Call<Reports> call, Response<Reports> response) {
                if(response.isSuccessful()) {
                    reports = response.body().getReports();
                    listView.setAdapter(new CustomAdaptaterViewReports(getContext(), reports));
                }
            }

            @Override
            public void onFailure(Call<Reports> call, Throwable t) {
                Intent myIntent = new Intent(getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public ArrayList<Report> getReports(){
        return reports;
    }
}
