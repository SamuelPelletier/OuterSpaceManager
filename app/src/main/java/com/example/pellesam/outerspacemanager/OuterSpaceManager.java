package com.example.pellesam.outerspacemanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by pellesam on 06/03/2017.
 */

public interface OuterSpaceManager {
    @POST("/api/v1/auth/create")
    Call<User> createUser(@Body User user);

    @POST("/api/v1/auth/login")
    Call<User> connectUser(@Body User user);

    @GET("/api/v1/users/get")
    Call<User> getUser(@Header("x-access-token") String token);

    @GET("/api/v1/buildings/list")
    Call<Buildings> getBuilding(@Header("x-access-token") String token);

    @POST("/api/v1/buildings/create/{id}")
    Call<Building> constructBuilding(@Header("x-access-token") String token, @Path("id") Integer idBuilding);

    @GET("/api/v1/users/{start}/{end}")
    Call<Users> getUsers(@Header("x-access-token") String token, @Path("start") Integer start, @Path("end") Integer end);

    @GET("/api/v1/searches/list")
    Call<Searches> getSearches(@Header("x-access-token") String token);

    @POST("/api/v1/searches/create/{id}")
    Call<Search> doSearch(@Header("x-access-token") String token, @Path("id") Integer idSearch);

}
