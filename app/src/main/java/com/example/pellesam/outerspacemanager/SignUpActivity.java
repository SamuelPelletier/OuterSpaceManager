package com.example.pellesam.outerspacemanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pellesam on 06/03/2017.
 */

public class SignUpActivity extends Activity implements View.OnClickListener{

    private Button btnValider;
    private EditText fieldId;
    private EditText fieldPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnValider = (Button) findViewById(R.id.buttonValidate);
        fieldId = (EditText) findViewById(R.id.FieldId);
        fieldPassword = (EditText) findViewById(R.id.FieldPassword);
        btnValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceManager service = retrofit.create(OuterSpaceManager.class);
                Call<User> request = service.createUser(new User(fieldId.getText().toString(), fieldPassword.getText().toString()));
                request.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("SUCCESS","ok");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("ERROR",t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

}
