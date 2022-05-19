package com.example.nutritionapplication.dto;



public class FoodTO {
    private long id;
    private String name;
    private String unitSize;

    public FoodTO(){
    }

    public FoodTO(long id, String name, String unitSize) {
        this.id = id;
        this.name = name;
        this.unitSize = unitSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }


    public String getUnitSize() {
        return unitSize;
    }


    public void setUnitSize(String unitSize) {
        this.unitSize = unitSize;
    }

    @Override
    public String toString() {
        String unit = "";
        if(unitSize.contains("ml")) unit = "Milliliter";
        else unit = "Gram";
        return "Name: " + name
                + "\n" +
                "Einheit: " + unit;
    }

}


