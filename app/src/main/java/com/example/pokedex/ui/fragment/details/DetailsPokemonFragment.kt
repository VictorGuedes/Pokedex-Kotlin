package com.example.pokedex.ui.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentDetailsPokemonBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsPokemonFragment : Fragment() {

    val infoViewModel : PokeInfoViewModel by viewModels()

    private lateinit var binding : FragmentDetailsPokemonBinding
    private lateinit var pokemonInfo : Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonInfo = arguments?.let { DetailsPokemonFragmentArgs.fromBundle(it).pokemonModel }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details_pokemon,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemonModel = pokemonInfo

        infoViewModel.getPokemonInfo(pokemonName = pokemonInfo.name)
        infoViewModel.pokemonInfo.observe(viewLifecycleOwner, androidx.lifecycle.Observer { info -> updateData(info) })
    }

    fun updateData(pokemonInfo: PokemonInfo){
        binding.pokemonName.text = pokemonInfo.name
        binding.typePokemon.text = pokemonInfo.types[0].type.name
        binding.heightPokemon.text = pokemonInfo.height.toString()
        binding.weightPokemon.text = pokemonInfo.weight.toString()

        binding.pregressHp.progress = pokemonInfo.status[0].baseStat
        binding.progressAttack.progress = pokemonInfo.status[1].baseStat
        binding.defense.progress = pokemonInfo.status[2].baseStat
        binding.specialAttack.progress = pokemonInfo.status[3].baseStat
        binding.specialDefense.progress = pokemonInfo.status[4].baseStat
        binding.speed.progress = pokemonInfo.status[5].baseStat

    }

}