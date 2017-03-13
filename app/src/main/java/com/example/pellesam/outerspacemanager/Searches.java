package com.example.pellesam.outerspacemanager;

import java.util.ArrayList;

/**
 * Created by mac14 on 13/03/2017.
 */

public class Searches {

    private ArrayList<Search> searches;

    public Searches(ArrayList<Search> searches) {
        this.searches = searches;
    }

    public ArrayList<Search> getSearches(){
        return this.searches;
    }

    public Integer getSize(){
        return this.searches.size();
    }
}
