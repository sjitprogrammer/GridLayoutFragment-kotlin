package com.example.testapplication.internet

import com.example.testapplication.internet.database.Pokemon
import com.example.testapplication.internet.database.PokemonList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object pointing to the desired URL
 * object.
 */
private val retrofit = Retrofit.Builder()
    // TODO (05) Change the ConverterFactory to the MoshiConverterFactory with our Moshi Object
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("pokemon?limit=151")
    fun fetchAllPokemon() : Call<PokemonList>

    @GET("pokemon/{id}")
    fun fetchPokemon(@Path("id") id:Int) : Call<Pokemon>

    @GET
    fun fetchPokemonData(@Url url: String?): Call<Pokemon>
}

object MyApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}