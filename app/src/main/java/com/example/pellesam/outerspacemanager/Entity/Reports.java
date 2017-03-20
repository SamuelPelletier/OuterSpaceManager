package com.example.pellesam.outerspacemanager.Entity;

import java.util.ArrayList;

/**
 * Created by mac14 on 20/03/2017.
 */

public class Reports {

    private ArrayList<Report> reports;

    public Reports(ArrayList<Report> buildings) {
        this.reports = reports;
    }

    public ArrayList<Report> getReports(){
        return this.reports;
    }

    public Integer getSize(){
        return this.reports.size();
    }

}
