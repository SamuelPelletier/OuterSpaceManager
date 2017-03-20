package com.example.pellesam.outerspacemanager.CustomActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pellesam.outerspacemanager.DAO.AttackDataSource;
import com.example.pellesam.outerspacemanager.Entity.Attack;
import com.example.pellesam.outerspacemanager.Entity.HttpResponse;
import com.example.pellesam.outerspacemanager.Entity.Ship;
import com.example.pellesam.outerspacemanager.Entity.Ships;
import com.example.pellesam.outerspacemanager.Entity.User;
import com.example.pellesam.outerspacemanager.MainActivity.MainActivity;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.internal.Internal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac14 on 13/03/2017.
 */

public class CustomAdaptaterViewAttack extends ArrayAdapter<User> implements View.OnClickListener{

    private final Context context;
    private final ArrayList<User> users;
    private final Ships ships;
    private final SharedPreferences sharedPreferences;
    public CustomAdaptaterViewAttack(Context context, ArrayList<User> users, Ships ships, SharedPreferences sharedPreferences) {
        super(context, R.layout.custom_attack_list, users);
        this.context = context;
        this.users = users;
        this.ships = ships;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_attack_list, parent, false);

        TextView username = (TextView) rowView.findViewById(R.id.username);
        TextView points = (TextView) rowView.findViewById(R.id.points);
        final Button attack = (Button) rowView.findViewById(R.id.buttonAttack);
        username.setText(users.get(position).getUsername());
        points.setText(String.valueOf(users.get(position).getPoints()));

        attack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                String username = users.get(position).getUsername();
                ArrayList<Ship> fleet = new ArrayList<Ship>();
                for(Integer i = 0;i<ships.getSize(); i++) {
                    fleet.add(new Ship(ships.getShips().get(i).getShipId(), ships.getShips().get(i).getAmount()));
                }

                OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
                final Call<HttpResponse> request = service.attack(sharedPreferences.getString("tokenId", "noToken"), username, new Ships(fleet));
                request.enqueue(new Callback<HttpResponse>() {
                    CharSequence text = "Erreur de connexion";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);

                    @Override
                    public void onResponse(Call<HttpResponse> call, Response<HttpResponse> response) {
                        if(response.isSuccessful()){
                            CharSequence text = "Attaque envoyé !";
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            Intent myIntent = new Intent(context, MainActivity.class);
                            AttackDataSource attackDataSource = new AttackDataSource(getContext());
                            attackDataSource.open();
                            attackDataSource.createAttack(String.valueOf(response.body().getAttackTime()),"");
                            attackDataSource.close();
                            context.startActivity(myIntent);
                        }else {
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResponse> call, Throwable t) {
                        toast.show();
                    }
                });
            }
        });
        return rowView;
    }

    @Override
    public void onClick(View v) {

    }
}