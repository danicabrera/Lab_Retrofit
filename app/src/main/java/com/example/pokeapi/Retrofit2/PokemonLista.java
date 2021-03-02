package com.example.pokeapi.Retrofit2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonLista {
    public int count;
    public String next;
    public String previous;
    @SerializedName("results")
    public ArrayList<Pokemon> pokedex;

    public ArrayList<Pokemon> getPokedex() {
        return pokedex;
    }

    public void setPokedex(ArrayList<Pokemon> pokedex) {
        this.pokedex = pokedex;
    }
}
