package com.example.nutritionapplication.dto;


public class FoodDTO {
    private int id;
    private String name;
    private int proteine;
    private int kalorien;
    private String menge;

    public void setId(int id) {
        this.id = id;
    }

    public FoodDTO(int id, String name, int proteine, int kalorien, String menge) {
        this.id = id;
        this.name = name;
        this.proteine = proteine;
        this.kalorien = kalorien;
        this.menge = menge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProteine(int proteine) {
        this.proteine = proteine;
    }

    public void setKalorien(int kalorien) {
        this.kalorien = kalorien;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProteine() {
        return proteine;
    }

    public int getKalorien() {
        return kalorien;
    }

    public String getMenge() {
        return menge;
    }


}
