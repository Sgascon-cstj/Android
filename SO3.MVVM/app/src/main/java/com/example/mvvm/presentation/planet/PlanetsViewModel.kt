package com.example.mvvm.presentation.planet

import androidx.lifecycle.ViewModel
import com.example.mvvm.data.repositories.PlanetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class PlanetsViewModel : ViewModel(){

    private val planetRepository = PlanetRepository()
    private val  _planetUiState = MutableStateFlow<PlanetsUIState>(PlanetsUIState.Empty)
    val planetsUIState = _planetUiState.asStateFlow()

    init {
        _planetUiState.update {
            return@update PlanetsUIState.Success(planetRepository.retrievePlanets())
        }
    }

}