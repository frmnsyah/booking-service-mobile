package com.appsaja.wagiman.bookingservices;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App {

    public static final String KEY_API = "7888a463f565559cca191816a88cda37";
//    public static final String BASE_URL = "http://booking-skripsi.atwebpages.com/api/";
    public static final String BASE_URL = "http://192.168.1.5/api/";
    public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/";
    public static final String TAG_ID_SERVICE = "TAG_ID_SERVICE";
    public static final String TAG_ID_MOVIE = "TAG_ID_MOVIE";
    public static final String TAG_ID_TV = "TAG_ID_TV";
    public static final String TAG_PATH_MOVIE = "TAG_PATH_MOVIE";
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

    public static void setImageRs(Context context,String url, ImageView img){
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(img);
    }
}
