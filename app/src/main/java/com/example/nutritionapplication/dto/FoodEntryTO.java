package com.example.nutritionapplication.dto;
public class FoodEntryTO {
    private long id;
    private FoodTO food;
    private int quantity;
    private int calories;
    private double fats;
    private double carbs;
    private double proteins;

    public FoodEntryTO() {
    }

    public FoodEntryTO(Long id, FoodTO food, int quantity, double fats, double carbs, double proteins) {
        this.id = id;
        this.food = food;
        this.quantity = quantity;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public FoodTO getFood() {
        return food;
    }

    public void setFood(FoodTO food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    @Override
    public String toString() {
        String unit = "";
        if(getFood().getUnitSize().contains("ml")) unit = "ml";
        else unit = "g";
        return this.getFood().getName().toUpperCase() +
                "\n" + "Eintrag Kalorien: " + calories + "    " + quantity + unit
                + "\n" +
                "Eiweiß: " + Math.round(proteins*100.0)/100.0 + "\n" +
                "Kohlenhydrate: " + Math.round(carbs*100.0)/100.0 + "\n" +
                "Fette: " + Math.round(fats*100.0)/100.0;
    }
}
