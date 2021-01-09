package com.example.pokedex.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadPokemonImage(imageView: ImageView, url : String){
        val id = url.split("/")
        Glide.with(imageView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id[6] + ".png")
            .into(imageView)

    }


}