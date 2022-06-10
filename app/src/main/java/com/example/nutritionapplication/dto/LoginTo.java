package com.example.nutritionapplication.dto;
import android.provider.ContactsContract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LoginTo {
        private String email;
        private String password;
    public LoginTo(String emial,String password){
        this.email=emial;
        this.password=password;
    }
    }


