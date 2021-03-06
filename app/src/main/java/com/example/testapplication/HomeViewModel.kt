package com.example.testapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplication.internet.MyApi
import com.example.testapplication.internet.database.Pokemon
import com.example.testapplication.internet.database.PokemonList
import com.example.testapplication.internet.database.ResultList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    private val _pokemonFetchAll = MutableLiveData<List<ResultList>>()
    val _pokemonList = MutableLiveData<List<Pokemon>>()
    private val list: ArrayList<Pokemon> = ArrayList()
    var homeListener: HomeListener? = null

//    init {
//        fetchAllPokemon()
//    }

    fun fetchAllPokemon() {
        _pokemonFetchAll.value = ArrayList()
        _pokemonList.value = ArrayList()
        list.clear()
        homeListener?.fetchAllPokemon()
        MyApi.retrofitService.fetchAllPokemon().enqueue(object : Callback<PokemonList> {
            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                Log.e(TAG, "error : " + t)
            }

            override fun onResponse(
                call: Call<PokemonList>,
                response: Response<PokemonList>
            ) {
                if (response.isSuccessful) {

                    _pokemonFetchAll.value = response.body()?.results
                    Log.e(TAG, _pokemonFetchAll.toString())
                    fetchPokemonData();

                } else {
                    homeListener?.onFailure(response.errorBody().toString())
                }
            }

        })
    }

    private fun fetchPokemonData() {
        val count = _pokemonFetchAll.value?.size
        Log.e(TAG, "_pokemonFetchAll : $count")
        var i:Int = 0
        _pokemonFetchAll.value?.forEach {
            
            MyApi.retrofitService.fetchPokemonData(it.url).enqueue(object : Callback<Pokemon> {
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.e(TAG, "error: ${i} : " + t)
                }

                override fun onResponse(
                    call: Call<Pokemon>,
                    response: Response<Pokemon>
                ) {

                    if (response.isSuccessful) {
//                        Log.e(TAG, "fetchPokemonData")
                        val body = response.body()
                        val pokemon: Pokemon = body!!
//                        Log.e(TAG + 1, pokemon.toString())

                        list.add(pokemon)
                        _pokemonList.value = list
//                        Log.e(TAG, "pokemon size : " + list.size)
                    }

                }

            })
        }

    }

    fun LoadPokemonFinish() {
        homeListener?.onSuccess()
    }
}