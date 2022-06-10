package com.example.nutritionapplication.service;

import com.example.nutritionapplication.dto.FoodTO;
import com.example.nutritionapplication.dto.MealTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FoodService {
    @GET("/rest/food/name={name}")
    public Call<List<FoodTO>> getSearchResults(@Header ("Authorization") String jwt, @Path("name") String name);
}
