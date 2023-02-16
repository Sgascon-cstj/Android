package com.example.mvvm.domain.models

//Planet -> planetRepository -> PlanetViewModel(Utiliser pour gérer les donnés et les état qui sont retourner à la vue) -> planetUiState(permet d'avoir tout les état) ->
data class Planet(
    val name: String,
    val temperature: Double,
    val image: String
)
