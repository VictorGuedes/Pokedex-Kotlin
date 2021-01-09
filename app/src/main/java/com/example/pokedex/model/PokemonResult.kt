package com.example.pokedex.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResult(
    @field:Json( name = "results") val pokemons : List<Pokemon>
)