package com.example.mvvm.presentation.planet.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.domain.models.Planet

class PlanetRecyclerViewAdapter(val planets: List<Planet>) : RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = planets.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    inner class ViewHolder() : RecyclerView.ViewHolder(){

    }
}