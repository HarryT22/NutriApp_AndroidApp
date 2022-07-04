package com.example.nutritionapplication.dto;

import java.util.Date;
import java.util.Set;

public class MealTO {

    private long id;
    private Date date;
    private double proteins;
    private double carbs;
    private double fats;
    private int calories;
    private String mealCategory;
    private Set<FoodEntryTO> foodEntries;


    public MealTO(){
    }

    public MealTO(long id, Date date, double proteins, double carbs, double fats, int calories, String mealCategory) {
        this.id = id;
        this.date = date;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
        this.calories = calories;
        this.mealCategory = mealCategory;
    }

    public Set<FoodEntryTO> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodList(Set<FoodEntryTO> foodEntries) {
        this.foodEntries = foodEntries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    @Override
    public String toString() {
        return mealCategory.toUpperCase() + "    Gesamtkalorien: " + calories + "\n" +
            "Eiweiß: " + Math.round(proteins*100.0)/100.0 + "\n" +
            "Kohlenhydrate: " + Math.round(carbs*100.0)/100.0 + "\n" +
            "Fette: " + Math.round(fats*100.0)/100.0;
    }
}

