package com.example.pokedex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonInfo
import com.example.pokedex.model.PokemonResult
import com.example.pokedex.service.PokedexService
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokedexService: PokedexService
) {

    fun getPokemonList(limit : Int, offset : Int) : Observable<PokemonResult> {
        return pokedexService.getPokemons(limit = limit, offset = offset)
    }

    fun getPokemonsPaged() : Flowable<PagingData<Pokemon>>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PokemonsPagingSource(pokedexService) }
            ).flowable
    }

    fun getPokemonInfo(pokemonName : String) : Observable<PokemonInfo>{
        return pokedexService.getPokemonInfo(pokemonName)
    }

}