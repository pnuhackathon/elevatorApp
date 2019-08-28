package com.example.studyroomsystem;

public class UserModel {

    public String email;
    public String key;
    public String name;
    public String authority;
    public String reservation;
    public String pushToken;
    public String schoolid;

    public UserModel(){

    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {return authority;}
    public void setAuthority(String authority) { this.authority = authority; }

    public String getReservation() {
        return reservation;
    }
    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) { this.key = key; }

    public String getPushToken() {
        return pushToken;
    }
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}
