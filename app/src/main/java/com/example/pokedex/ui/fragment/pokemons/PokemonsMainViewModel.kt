package com.example.pokedex.ui.fragment.pokemons

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava3.cachedIn
import com.example.pokedex.model.Pokemon
import com.example.pokedex.repository.PokemonRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class PokemonsMainViewModel @ViewModelInject constructor(
    val pokemonRepository: PokemonRepository
) : ViewModel() {

    var pokemonList : MutableLiveData<List<Pokemon>> = MutableLiveData()

    fun getPokemonList(){
        pokemonRepository.getPokemonList(10, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {response -> pokemonList.value = response.pokemons},
                {t -> Log.d("erro", t.localizedMessage) }
            )
    }

    fun getPokemonPaged() : Flowable<PagingData<Pokemon>>{
        return pokemonRepository.getPokemonsPaged()
            .map { pagingData -> pagingData.filter { true } }
            .cachedIn(viewModelScope)
    }




}