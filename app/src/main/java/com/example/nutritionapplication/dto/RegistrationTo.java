package com.example.nutritionapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationTo {
    private  String name;
    private  String userName;
    private   String email;
    private  String password;
    private   AppUserZiele appUserZiele ;
    private  short groesse;
    private  short  gewicht;
    private  String geburtsdatum;
    private  Gender gender;
    private  Role role;

    public RegistrationTo(String name, String userName, String email, String password, AppUserZiele appUserZiele, short groesse, short gewicht, String geburtsdatum, Gender gender, Role role) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUserZiele = appUserZiele;
        this.groesse = groesse;
        this.gewicht = gewicht;
        this.geburtsdatum = geburtsdatum;
        this.gender = gender;
        this.role = role;
    }
}
