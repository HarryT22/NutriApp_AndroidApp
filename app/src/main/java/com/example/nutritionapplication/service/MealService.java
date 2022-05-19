package com.example.nutritionapplication.service;

import com.example.nutritionapplication.dto.MealTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MealService {
    @GET("/rest/meal/{id}")
    public Call<MealTO> getMeal(@Path("id") long id);

    @GET("/rest/meal/{day}/{month}/{year}/{email}")
    public Call<List<MealTO>> getDailyMeals(@Path("day") int day, @Path("month") int month, @Path("year") int year, @Path("email") String email);

    @POST("/rest/meal/{day}/{month}/{year}/{mealcategory}/{email}")
    public Call<Void> createMeal(@Path("day") int day, @Path("month") int month, @Path("year") int year, @Path("mealcategory") String mealcategory, @Path("email") String email);

    @DELETE("/rest/meal/{mealId}/{foodId}")
    public Call<Void> deleteFood(@Path("mealId") long mealId, @Path("foodId") long foodId);

    @POST("/rest/meal/{mealId}/{foodId}/{quantity}")
    public Call<Void> addFood(@Path("mealId") long mealId, @Path("foodId") long foodId, @Path("quantity") int quantity);


}
