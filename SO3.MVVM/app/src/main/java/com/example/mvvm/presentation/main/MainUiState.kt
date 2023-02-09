package com.example.mvvm.presentation.main

import com.example.mvvm.domain.models.Pilot

//class PilotUiState(val isSuccess: Boolean, val pilot: Pilot) {
  sealed class MainUiState{//sealed: plus personne ne peut errité de ca sauf mes enfant
      class Success(val pilot:Pilot) : MainUiState()
      object Loading : MainUiState() //N'est pas toujours présente
      class Error(val message: String) : MainUiState()
      object Empty : MainUiState() //N'est pas toujours présente
  }

    //isError => pilot == null
    //isSuccess => pilot = Pilot()
    //isLoading => pilot = null
    //isEmpty => pilot == null
//}