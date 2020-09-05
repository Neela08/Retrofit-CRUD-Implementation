package com.bitwisey.retrofitcrudapp.Model;

import com.google.gson.annotations.SerializedName;

public class RetroInfo {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("number")
    private String number;

    public RetroInfo( String name, String number,int id) {

        this.name = name;

        this.number = number;
        this.id=id;

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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}


