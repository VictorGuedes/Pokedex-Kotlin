package com.example.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.databinding.ItemPokemonsListBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.fragment.pokemons.PokemonsMainListFragmentDirections

class PokemonListAdapter(context : Context) : RecyclerView.Adapter<PokemonListAdapter.ViewHolderPokemonList>() {

    private val items : MutableList<Pokemon> = mutableListOf()
    private val context : Context = context

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

    fun setPokemonList(pokemonList : List<Pokemon>){
        items.clear()
        items.addAll(pokemonList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderPokemonList, position: Int) {
        holder.binding.apply {
            pokemonModel = items[position]
            cardView.setOnClickListener { gotoDetails(it, items[position]) }
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
}