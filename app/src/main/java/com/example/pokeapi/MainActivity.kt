package com.example.pokeapi

import android.app.Activity
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapi.Retrofit2.ApiRest
import com.example.pokeapi.Retrofit2.PokemonLista
import com.example.pokeapi.Retrofit2.Urls
import com.example.pokeapi.Retrofit2.Utils
import com.example.pokeapi.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var retrofit: Retrofit? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val busqueda: Button = findViewById(R.id.btn)
        val Height: TextView = findViewById(R.id.txtHeight)
        val Vista: TextView = findViewById(R.id.txt)
        val Pokemon: EditText = findViewById(R.id.txtPoke)


        val espacio = " "


        busqueda.setOnClickListener {
            val retrofit2 = ApiRest()
            val buscando = Pokemon.text.toString()


            if (buscando.length > 50) {
                val toast = Toast.makeText(applicationContext, "La busqueda no puede exceder los 50 caracteres", Toast.LENGTH_SHORT)
                toast.show()
                Vista.text = ""
            }

            if(busqueda.text == " ") {
                val toast = Toast.makeText(applicationContext, "La busqueda no puede contener espacios", Toast.LENGTH_SHORT)
                toast.show()
                Vista.text = ""
            }

            val call: Call<JsonObject> = retrofit2.getService().getPokemonID(buscando)

            call.enqueue(object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful){
                        val pokemon: JsonObject? = response.body()
                        val name = pokemon!!["name"].asString


                        var datos = ("\n Nombre: " + name
                                + "\n Altura: ${pokemon!!["height"]}"
                                + "\n Peso: ${pokemon!!["weight"]}"
                                + "\n Experiencia Inicial: ${pokemon!!["base_experience"]}")
                        Vista.text = datos
                        Log.i("detalle", pokemon.toString())
                    } else{0
                        Log.e("error", "Hubo un error!")
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e("error", t.toString())
                }
            })



        }
    }


}