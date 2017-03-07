package com.example.pellesam.outerspacemanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by pellesam on 06/03/2017.
 */

public interface OuterSpaceManager {
    @POST("/api/v1/auth/create")
    Call<User> createUser(@Body User user);

    @GET("/api/v1/users/get")
    Call<User> getUser(@Header("x-access-token") String token);

    @GET("/api/v1/buildings/list")
    Call<Buildings> getBuilding(@Header("x-access-token") String token);

}
