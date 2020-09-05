package com.bitwisey.retrofitcrudapp.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static final String BASE_URL="https://your domain name/folder name/";//if using localhost , put your localhost ip
    private static Client myClient;
    private Retrofit retrofit;

    private Client(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized Client getInstance(){
        if (myClient==null){
            myClient=new Client();
        }
        return myClient;
    }
    public Api getMyApi(){
        return retrofit.create(Api.class);
    }
}