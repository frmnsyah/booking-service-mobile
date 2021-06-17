package com.appsaja.wagiman.bookingservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App {

    public static final String BASE_URL = "http://booking-skripsi.atwebpages.com/api/";
//    public static final String BASE_URL = "http://192.168.1.5/api/";
    public static final String TAG_ID_SERVICE = "TAG_ID_SERVICE";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory((GsonConverterFactory.create(gson)))
                    .build();
        }

        return retrofit;
    }
}
