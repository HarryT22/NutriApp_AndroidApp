package com.example.nutritionapplication.dto;
import android.provider.ContactsContract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginTo {
        private String email;
        private String password;
        private Role role;
    }


