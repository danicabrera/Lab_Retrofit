package com.example.pokeapi.Retrofit2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiRest() {
    private var service: Urls
    var Utils = Utils()

    init {

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(Urls::class.java)

    }

    fun getService(): Urls{
        return service
    }
}


