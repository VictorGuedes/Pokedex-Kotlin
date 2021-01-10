package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.databinding.ItemPokemonsListBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.fragment.pokemons.PokemonsMainListFragmentDirections

class PokePagedAdapter : PagingDataAdapter<Pokemon, PokePagedAdapter.ViewHolderPokemonList>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPokemonList {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPokemonsListBinding>(
            inflater,
            R.layout.item_pokemons_list,
            parent,
            false
        )

        return ViewHolderPokemonList(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderPokemonList, position: Int) {
        holder.binding.apply {
            pokemonModel = getItem(position)
            cardView.setOnClickListener { getItem(position)?.let { it1 -> gotoDetails(it, it1) } }
            executePendingBindings()
        }
    }

    fun gotoDetails(view : View, pokemonItem : Pokemon) {
        val action = PokemonsMainListFragmentDirections.actionPokemonsMainListToDetailsPokemonFragment(
            pokemonItem
        )
        view.findNavController().navigate(action)
    }

    class ViewHolderPokemonList(
        val binding: ItemPokemonsListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }


}