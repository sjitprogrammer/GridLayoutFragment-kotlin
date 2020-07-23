package com.example.testapplication

interface HomeListener {
    fun fetchAllPokemon()
    fun onSuccess()
    fun onFailure(message: String)
}