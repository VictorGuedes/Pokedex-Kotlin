package com.example.pokedex.ui.fragment.details

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.model.PokemonInfo
import com.example.pokedex.repository.PokemonRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class PokeInfoViewModel @ViewModelInject constructor(
    val pokemonRepository: PokemonRepository
) : ViewModel() {

    var pokemonInfo : MutableLiveData<PokemonInfo> = MutableLiveData()


    fun getPokemonInfo(pokemonName : String){
        pokemonRepository.getPokemonInfo(pokemonName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {response -> pokemonInfo.value = response},
                {t -> Log.d("erro", t.localizedMessage) }
            )
    }
}