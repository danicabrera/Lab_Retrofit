package com.example.pokeapi.Retrofit2

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Urls {
    @GET("{id}")
    fun getPokemonID(@Path("id") id:String): Call<JsonObject>
}