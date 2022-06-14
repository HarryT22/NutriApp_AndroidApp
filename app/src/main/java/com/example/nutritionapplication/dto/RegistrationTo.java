package com.example.nutritionapplication.dto;



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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserZiele getAppUserZiele() {
        return appUserZiele;
    }

    public void setAppUserZiele(AppUserZiele appUserZiele) {
        this.appUserZiele = appUserZiele;
    }

    public short getGroesse() {
        return groesse;
    }

    public void setGroesse(short groesse) {
        this.groesse = groesse;
    }

    public short getGewicht() {
        return gewicht;
    }

    public void setGewicht(short gewicht) {
        this.gewicht = gewicht;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegistrationTo{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", appUserZiele=" + appUserZiele +
                ", groesse=" + groesse +
                ", gewicht=" + gewicht +
                ", geburtsdatum='" + geburtsdatum + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                '}';
    }
}
