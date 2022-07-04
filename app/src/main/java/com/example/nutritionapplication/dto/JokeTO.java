package com.example.nutritionapplication.dto;

import java.util.Map;

public class JokeTO {

    public boolean error;
    public String category;
    public String type;
    public String joke;
    public Map<String, Boolean> flags;
    public int id;
    public boolean safe;
    public String lang;


    public JokeTO() {}

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
