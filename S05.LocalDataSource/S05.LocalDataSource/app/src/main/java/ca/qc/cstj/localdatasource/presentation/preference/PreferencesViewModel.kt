package ca.qc.cstj.localdatasource.presentation.preference


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.localdatasource.domain.repositories.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PreferencesViewModel(application : Application) : AndroidViewModel(application) {


    private val _preferencesUiState = MutableStateFlow<PreferencesUiState>(PreferencesUiState.Empty)
    val preferencesUiState = _preferencesUiState.asStateFlow()
    private val userPreferencesRepository = UserPreferencesRepository(application)

    init {
        viewModelScope.launch {
            userPreferencesRepository.userPreference.collect{userPreference->
                _preferencesUiState.update {
                    PreferencesUiState.Success(userPreference)
                }
            }

        }
    }
    fun saveName(name: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveName(name)
        }
    }
    fun saveDarkMode(isDarkModeOn: Boolean) {
        viewModelScope.launch {
        userPreferencesRepository.saveDarkMode(isDarkModeOn)
        }
    }
}