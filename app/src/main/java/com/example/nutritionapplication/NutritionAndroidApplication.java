package com.example.nutritionapplication;

import android.app.Application;

import com.example.nutritionapplication.dto.FoodEntryTO;
import com.example.nutritionapplication.dto.MealTO;
import com.example.nutritionapplication.service.FoodService;
import com.example.nutritionapplication.service.IUserService;
import com.example.nutritionapplication.service.MealService;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NutritionAndroidApplication extends Application {

    private MealService mealService;
    private FoodService foodService;
    private IUserService iUserService;
    private String jwt;
    private Date date;

    public NutritionAndroidApplication() {
        Retrofit retrofit = new Retrofit.Builder()
                //replace url in next line by the address of the virtual machine running your shopping list service
                .baseUrl("http://10.0.2.2:8080") // 10.0.2.2 stands for localhost
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.mealService = retrofit.create(MealService.class);
        this.foodService = retrofit.create(FoodService.class);
        this.iUserService=retrofit.create(IUserService.class);
        this.date = new Date();
    }

    public MealService getMealService() { return  this.mealService; }

    public FoodService getFoodService() {return  this.foodService;}

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public IUserService getIUserService() {
        return this.iUserService;
    }
    public String getJwt() {
        return jwt;
    }
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }}
