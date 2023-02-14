package com.example.mvvm.presentation.planet

import com.example.mvvm.domain.models.Planet

sealed class PlanetsUIState {
    object  Empty : PlanetsUIState()
    class Success(val planets: List<Planet>) : PlanetsUIState()
}