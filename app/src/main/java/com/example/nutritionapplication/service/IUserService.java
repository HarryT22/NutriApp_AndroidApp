package com.example.nutritionapplication.service;

import com.example.nutritionapplication.dto.LoginTo;
import com.example.nutritionapplication.dto.RegistrationTo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserService {
    @POST("/rest/login")
    public Call<ResponseBody> login(@Body LoginTo userTO);

    @POST("/rest/registration")
    public Call<ResponseBody> register(@Body RegistrationTo RegisterTO);
}
