package com.example.pellesam.outerspacemanager.CustomActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pellesam.outerspacemanager.Entity.User;
import com.example.pellesam.outerspacemanager.R;

import java.util.ArrayList;

/**
 * Created by mac14 on 13/03/2017.
 */

public class CustomAdaptaterViewUsers extends ArrayAdapter<User> {

    private final Context context;
    private final ArrayList<User> users;
    public CustomAdaptaterViewUsers(Context context, ArrayList<User> users) {
        super(context, R.layout.custom_galaxy_list, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_galaxy_list, parent, false);

        TextView number = (TextView) rowView.findViewById(R.id.number);
        TextView username = (TextView) rowView.findViewById(R.id.username);
        TextView points = (TextView) rowView.findViewById(R.id.points);

        Integer userPosition = position +1;
        switch (userPosition)
        {
            case 1:
                username.setTextColor(Color.rgb(255,215,0));
            break;
            case 2:
                username.setTextColor(Color.rgb(75,75,75));
                break;
            case 3:
                username.setTextColor(Color.rgb(80,50,20));
                break;
            default:
        }

        number.setText("TOP : "+String.valueOf(userPosition));
        username.setText(users.get(position).getUsername());
        points.setText(String.valueOf(users.get(position).getPoints()));

        return rowView;
    }

}