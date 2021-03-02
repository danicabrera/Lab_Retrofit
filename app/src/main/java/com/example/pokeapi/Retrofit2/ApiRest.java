package com.example.pokeapi.Retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRest {

    final private Urls urls;
    public ApiRest(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        urls = retrofit.create(Urls.class);


    }

    public Urls getService(){
        return urls;
    }

}
