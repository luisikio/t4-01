package com.example.t4_01.services;

import com.example.t4_01.entities.Animes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface servicesWeb {

    @GET("Animes/")
    Call<List<Animes>> getContacts();

    @GET("Animes/{id}")
    Call<Animes> findContact(@Path("id") int id);

    @POST("Animes")
    Call<Animes> create(@Body Animes animes);
}
