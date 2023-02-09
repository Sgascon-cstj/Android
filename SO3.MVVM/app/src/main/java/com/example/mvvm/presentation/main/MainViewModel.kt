package com.example.mvvm.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.core.Constants
import com.example.mvvm.domain.models.Pilot
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel : ViewModel(){

    private val pilot = Pilot("Samuel", 15)

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Empty)//Indiquer MainUiState comme <MainUiState> sinon il sera impossible d'assigner les autres types
    val mainUiState = _mainUiState.asStateFlow()

    init {
        _mainUiState.update {
            return@update MainUiState.Success(pilot)// Peut aussi mettre MainUiState.Success(pilot) seulement la dernière instruction va automatiquement return
        }
    }
    fun fly(revolution: Int, isTraining: Boolean )
    {
        if (pilot.canFly())
        {

            viewModelScope.launch {
                //1.Changement d'état pour loading(animating)
                _mainUiState.update {
                    MainUiState.Loading
                }
                //2. Attendre x millisecondes
                delay(Constants.REVOLUTION_DURATION * revolution)
                //3. Voler
                pilot.fly(revolution,isTraining)
                //4. Changement d'état pour success
                _mainUiState.update {
                    MainUiState.Success(pilot)
                }
            }
        }
        else
        {
            _mainUiState.update {
                MainUiState.Error("Can't fly")
            }
        }
    }

    fun recharge() {
        pilot.recharge()
        _mainUiState.update {
            MainUiState.Success(pilot)
        }
    }

}