package com.i190405.task2;

import android.graphics.Bitmap;

public class MyModel {

    String name,number,address;
    Bitmap dp;
    public Bitmap getDp() {
        return dp;
    }

    public void setDp(Bitmap dp) {
        this.dp = dp;
    }


    public MyModel(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
