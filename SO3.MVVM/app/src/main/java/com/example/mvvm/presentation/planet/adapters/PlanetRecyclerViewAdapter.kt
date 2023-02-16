package com.example.mvvm.presentation.planet.adapters

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.core.loadFromResource
import com.example.mvvm.databinding.ItemPlanetBinding
import com.example.mvvm.domain.models.Planet
//Adapter qui fait le lien entre le item_planet et le recyclerView de activity_planet
class PlanetRecyclerViewAdapter(var planets: List<Planet>) : RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       //Créer un viewHolder pour chaque planète
        //Charger en mémoire un item_planet
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = planets.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val planet = planets[position]
        holder.bind(planet)
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ItemPlanetBinding.bind(view)

        fun bind(planet: Planet){
            binding.txvPlanetName.text = planet.name
            binding.txvPlanetTemperature.text = String.format("%.2f", planet.temperature)
            val imagePlanet = "planet${planet.image}"

            binding.imvItemPlanet.loadFromResource(imagePlanet)
        }
    }
}