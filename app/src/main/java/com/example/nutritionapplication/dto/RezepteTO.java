package com.example.nutritionapplication.dto;

import java.io.Serializable;
import java.util.List;


public class RezepteTO implements Serializable {
    /**
     * This class is a transfer object for the recipe class and adds some new attributes like gesamtzeit, kalorien and proteine.
     */
    private int id;
    private String name;
    private List<FoodDTO> foods;
    private int arbeitszeit;
    private int kochzeit;
    private int portionen;
    private String menueart;
    private boolean isVegan;
    private boolean isVegetarisch;
    private boolean fructose;
    private boolean lactose;
    private boolean histamine;
    private int gesamtzeit;
    private int kalorien;
    private int proteine;
    private String author;
    private String image;


    public RezepteTO(int id, String name, List<FoodDTO> foods, int arbeitszeit, int kochzeit, int portionen, String menueart, boolean isVegan, boolean isVegetarisch,
                     boolean fructose, boolean lactose, boolean histamine, int gesamtzeit, int kalorien, int proteine, String author, String image) {
        this.id = id;
        this.name = name;
        this.foods = foods;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.portionen = portionen;
        this.menueart = menueart;
        this.isVegan = isVegan;
        this.isVegetarisch = isVegetarisch;
        this.fructose = fructose;
        this.lactose = lactose;
        this.histamine = histamine;
        this.gesamtzeit = gesamtzeit;
        this.kalorien = kalorien;
        this.proteine = proteine;
        this.author = author;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public int getArbeitszeit() {
        return arbeitszeit;
    }

    public int getKochzeit() {
        return kochzeit;
    }

    public int getPortionen() {
        return portionen;
    }

    public String getMenueart() {
        return menueart;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isVegetarisch() {
        return isVegetarisch;
    }

    public boolean isFructose() {
        return fructose;
    }

    public boolean isLactose() {
        return lactose;
    }

    public boolean isHistamine() {
        return histamine;
    }

    public int getGesamtzeit() {
        return gesamtzeit;
    }

    public int getKalorien() {
        return kalorien;
    }

    public int getProteine() {
        return proteine;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }

    public void setArbeitszeit(int arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    public void setKochzeit(int kochzeit) {
        this.kochzeit = kochzeit;
    }

    public void setPortionen(int portionen) {
        this.portionen = portionen;
    }

    public void setMenueart(String menueart) {
        this.menueart = menueart;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public void setVegetarisch(boolean vegetarisch) {
        isVegetarisch = vegetarisch;
    }

    public void setFructose(boolean fructose) {
        this.fructose = fructose;
    }

    public void setLactose(boolean lactose) {
        this.lactose = lactose;
    }

    public void setHistamine(boolean histamine) {
        this.histamine = histamine;
    }

    public void setGesamtzeit(int gesamtzeit) {
        this.gesamtzeit = gesamtzeit;
    }

    public void setKalorien(int kalorien) {
        this.kalorien = kalorien;
    }

    public void setProteine(int proteine) {
        this.proteine = proteine;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImage(String image) {
        this.image = image;
    }


}

