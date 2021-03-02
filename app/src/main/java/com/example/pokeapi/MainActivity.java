package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokeapi.Retrofit2.ApiRest;
import com.example.pokeapi.Retrofit2.Pokemon;
import com.example.pokeapi.Retrofit2.PokemonLista;
import com.example.pokeapi.Retrofit2.Urls;
import com.example.pokeapi.Retrofit2.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Pokedex";
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Button busqueda = findViewById(R.id.btn);
        TextView Height = findViewById(R.id.txtHeight);
        TextView Name = findViewById(R.id.txt);
        TextView Pokemon = findViewById(R.id.txtPoke);


        obtenerDatos();


    }

    private void obtenerDatos(){
        //ApiRest apiRest = new ApiRest();
        Urls service = retrofit.create(Urls.class);
        Call<PokemonLista> pokemonListaCall = service.getPokeLista();

        pokemonListaCall.enqueue(new Callback<PokemonLista>() {
            @Override
            public void onResponse(Call<PokemonLista> call, Response<PokemonLista> response) {
                if(response.isSuccessful()){

                    ArrayList<Pokemon> pokemonLista = response.body().getPokedex();
                    ArrayList<Pokemon> pokedey = pokemonLista;
                    for(int i = 0; i < pokedey.size(); i++){
                        Pokemon p = pokedey.get(i);
                        System.out.println("Pokemon: " + p.getName());

                    }


                }else{
                    Log.e(TAG, "Ha ocurrido un error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonLista> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }
}