package com.example.pokedex.repository

import com.example.pokedex.model.PokemonInfo
import com.example.pokedex.model.PokemonResult
import com.example.pokedex.service.PokedexService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokedexService: PokedexService
) {

    fun getPokemonList(limit : Int, offset : Int) : Observable<PokemonResult> {
        return pokedexService.getPokemons(limit = limit, offset = offset);
    }

    fun getPokemonInfo(pokemonName : String) : Observable<PokemonInfo>{
        return pokedexService.getPokemonInfo(pokemonName);
    }

}