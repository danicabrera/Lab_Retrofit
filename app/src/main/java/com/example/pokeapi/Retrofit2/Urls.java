package com.example.pokeapi.Retrofit2;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Urls {


    @GET("pokemon")
    Call<PokemonLista> getPokeLista();

}
