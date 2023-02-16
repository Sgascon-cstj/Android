package com.example.mvvm.presentation.planet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityPlanetsBinding
import com.example.mvvm.presentation.planet.adapters.PlanetRecyclerViewAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlanetsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanetsBinding// Étape 1 pour le binding
    private val viewModel : PlanetsViewModel by viewModels() //Ajouter une bibliotèque dans le gradle
    private lateinit var planetRecyclerViewAdapter: PlanetRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetsBinding.inflate(layoutInflater)//Étape 2 pour le binding

        setContentView(binding.root)//Étape 3 mettre binding root au lieu de R.dksjfhklsd.sakjdhaskd

        planetRecyclerViewAdapter = PlanetRecyclerViewAdapter(listOf())
        //binding.rcvPlanet.layoutManager = LinearLayoutManager(this)
        binding.rcvPlanet.layoutManager = GridLayoutManager(this, 1)
        binding.rcvPlanet.adapter = planetRecyclerViewAdapter
        viewModel.planetsUIState.onEach {
            when(it){
                is PlanetsUIState.Success ->{
                    planetRecyclerViewAdapter.planets = it.planets
                    planetRecyclerViewAdapter.notifyDataSetChanged()

                }
                is PlanetsUIState.Empty -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    companion object { // La patie static de la classe
        //Pour crée une nouvelle activity
        //Crée les 4 composant un qui se crée tout seul
        //Ex: le dossier plannet
        //Crée planet Activity - Planet UiState - Planet viewModel

        fun newIntent(context: Context) : Intent{
            return Intent(context, PlanetsActivity::class.java)
        }
    }
}