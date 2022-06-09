package com.example.nutritionapplication.service;

import com.example.nutritionapplication.dto.LoginTo;
import com.example.nutritionapplication.dto.RegistrationTo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserService {
    @POST("/user/login")
    public Call<ResponseBody> login(@Body LoginTo userTO);

    @POST("/api/v1/register")
    public Call<ResponseBody> register(@Body RegistrationTo RegisterTO);
}
