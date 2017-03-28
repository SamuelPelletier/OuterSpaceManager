package com.example.pellesam.outerspacemanager.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pellesam.outerspacemanager.Entity.User;
import com.example.pellesam.outerspacemanager.R;
import com.example.pellesam.outerspacemanager.Service.OuterSpaceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pellesam on 06/03/2017.
 */

public class SignUpActivity extends Activity implements View.OnClickListener{

    private Button btnCreate;
    private Button btnConnect;
    private EditText fieldId;
    private EditText fieldPassword;
    private EditText fieldEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences("TOKEN", 0);
        String token = settings.getString("tokenId", "noToken");
        if (!settings.getString("tokenId", "noToken").equals("noToken")){
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(myIntent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnConnect = (Button) findViewById(R.id.buttonConnect);
        fieldId = (EditText) findViewById(R.id.FieldId);
        fieldPassword = (EditText) findViewById(R.id.FieldPassword);
        fieldEmail = (EditText) findViewById(R.id.fieldEmail);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
                Call<User> request = service.createUser(new User(fieldId.getText().toString(), fieldPassword.getText().toString(), fieldEmail.getText().toString()));
                request.enqueue(new Callback<User>() {
                    Context context = getApplicationContext();
                    CharSequence text = "Erreur de création";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            if (response.body() == null) {
                                toast.show();
                            } else {
                                SharedPreferences settings = getSharedPreferences("TOKEN", 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("tokenId", response.body().getToken());
                                editor.commit();
                                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(myIntent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        toast.show();
                    }
                });
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
                Call<User> request = service.connectUser(new User(fieldId.getText().toString(), fieldPassword.getText().toString()));
                request.enqueue(new Callback<User>() {
                    Context context = getApplicationContext();
                    CharSequence text = "Erreur de connexion";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                        if(response.body() == null){
                            toast.show();
                        }else {
                            SharedPreferences settings = getSharedPreferences("TOKEN", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("tokenId", response.body().getToken());
                            editor.commit();
                            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(myIntent);
                        }
                    }
                    }


                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        toast.show();
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

}
