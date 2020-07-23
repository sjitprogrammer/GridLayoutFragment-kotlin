package com.example.testapplication.internet.database

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any?="",
    val results: List<ResultList>
)