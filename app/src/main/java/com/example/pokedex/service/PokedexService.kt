package com.example.pokedex.service

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon?")
    fun getPokemons(
        @Query("limit") limit : Int,
        @Query("offset") offset : Int
    ) : Observable<PokemonResult>

}