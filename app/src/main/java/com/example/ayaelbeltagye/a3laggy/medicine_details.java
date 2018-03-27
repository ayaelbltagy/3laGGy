package com.example.ayaelbeltagye.a3laggy;


import java.io.Serializable;

public class medicine_details implements Serializable {

    private String name;
    private String dose;
    private String image;
    private String time;

    public medicine_details() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDose() {
        return this.dose;
    }

    public void setDose(String dose) {

        this.dose = dose;
    }

    public String getImage() {

        return this.image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setImage(String image) {

        this.image = image;
    }
}

