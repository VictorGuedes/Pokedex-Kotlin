package com.example.pokedex.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class PokemonInfo(
    @field:Json(name = "id") val id : Int,
    @field:Json(name = "name") val name : String,
    @field:Json(name = "base_experience") val baseExperience : Int,
    @field:Json(name = "height") val height : Int,
    @field:Json(name = "weight") val weight : Int,
    @field:Json(name = "stats") val status : List<PokemonStatus>,
    @field:Json(name = "types") val types : List<PokemonType>

) : Serializable

@JsonClass(generateAdapter = true)
data class PokemonStatus(
    @field:Json(name = "base_stat") val baseStat : Int,
    @field:Json(name = "effort") val effort : Int,
    @field:Json(name = "stat") val stats : Pokemon
) : Serializable

@JsonClass(generateAdapter = true)
data class PokemonType(
    @field:Json(name = "type") val type : Pokemon
) : Serializable