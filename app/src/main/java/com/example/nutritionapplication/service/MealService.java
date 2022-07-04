package com.example.nutritionapplication.service;

import com.example.nutritionapplication.dto.JokeTO;
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
    public Call<MealTO> getMeal(@Header ("Authorization") String jwt, @Path("id") long id);

    @GET("/rest/meal/{day}/{month}/{year}")
    public Call<List<MealTO>> getDailyMeals(@Header ("Authorization") String jwt, @Path("day") int day, @Path("month") int month, @Path("year") int year);

    @GET("/rest/meal/tellAJoke/{category}")
    public Call<JokeTO> getJoke(@Header ("Authorization") String jwt, @Path("category") String category);

    @POST("/rest/meal/{day}/{month}/{year}/{mealcategory}")
    public Call<Void> createMeal(@Header ("Authorization") String jwt, @Path("day") int day, @Path("month") int month, @Path("year") int year, @Path("mealcategory") String mealcategory);

    @DELETE("/rest/meal/{mealId}/{foodId}")
    public Call<Void> deleteFood(@Header ("Authorization") String jwt, @Path("mealId") long mealId, @Path("foodId") long foodId);

    @POST("/rest/meal/{mealId}/{foodId}/{quantity}")
    public Call<Void> addFood(@Header ("Authorization") String jwt, @Path("mealId") long mealId, @Path("foodId") long foodId, @Path("quantity") int quantity);


}
