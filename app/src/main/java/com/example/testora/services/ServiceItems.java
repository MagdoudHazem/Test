package com.example.testora.services;

import com.example.testora.Entity.Items;
import com.example.testora.Entity.myitems;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceItems {

    public String url ="http://10.54.234.149:3000/";
    @GET("items")
    Call<List<myitems>> getAll();

    @POST("items/add")
    Call<myitems>createitems(@Body myitems items);


}
