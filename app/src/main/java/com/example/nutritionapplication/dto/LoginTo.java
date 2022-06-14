package com.example.nutritionapplication.dto;








public class LoginTo {
        private String email;
        private String password;
    public LoginTo(String emial,String password){
        this.email=emial;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginTo{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


