package com.example.pokedex.ui.fragment.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonsMainListBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.adapter.PokemonListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonsMainListFragment : Fragment() {

    private lateinit var binding : FragmentPokemonsMainListBinding

    val mainViewModel : PokemonsMainViewModel by viewModels()
    lateinit var adapterList : PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentPokemonsMainListBinding>(
            inflater,
            R.layout.fragment_pokemons_main_list,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterList = PokemonListAdapter(binding.root.context)
        binding.pokemonsRecyclerview.adapter = adapterList

        mainViewModel.pokemonList.observe(viewLifecycleOwner, androidx.lifecycle.Observer { list -> updateData(list) })
    }

    fun updateData(listPokemon : List<Pokemon>){
        adapterList.setPokemonList(listPokemon)
    }
}