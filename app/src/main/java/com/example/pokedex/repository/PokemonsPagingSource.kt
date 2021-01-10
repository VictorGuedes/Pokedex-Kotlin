package com.example.pokedex.repository

import androidx.paging.rxjava3.RxPagingSource
import com.example.pokedex.model.Pokemon
import com.example.pokedex.service.PokedexService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class PokemonsPagingSource constructor(
    private val pokedexService: PokedexService
) : RxPagingSource<Int, Pokemon>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Pokemon>> {
        val position = params.key ?: 0

        return pokedexService.getPokemonsPaged(offset = position)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it.pokemons, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data : List<Pokemon>, position : Int) : LoadResult<Int,Pokemon>{
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 0) null else position - 20,
            nextKey = if (position == 1118) null else position + 20
        )
    }
}