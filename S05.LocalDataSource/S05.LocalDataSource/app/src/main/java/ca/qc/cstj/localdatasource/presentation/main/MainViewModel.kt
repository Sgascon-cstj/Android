package ca.qc.cstj.localdatasource.presentation.main

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.localdatasource.core.AppDatabase
import ca.qc.cstj.localdatasource.domain.models.Note
import ca.qc.cstj.localdatasource.domain.models.UserPreferences
import ca.qc.cstj.localdatasource.domain.repositories.NoteRepository
import ca.qc.cstj.localdatasource.domain.repositories.UserPreferencesRepository
import ca.qc.cstj.localdatasource.presentation.note.NoteUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Empty)
    val mainUiState = _mainUiState.asStateFlow()
    private val noteRepository = AppDatabase.getInstance(application).noteRepository()
    private val userPreferencesRepository = UserPreferencesRepository(application)

    init {
        var notes : List<Note> = listOf()
        var preferences = UserPreferences()
        //Premier thread démarré
        viewModelScope.launch {
            noteRepository.retrieveAll().collect{
                notes = it
                _mainUiState.update {
                    return@update MainUiState.Success(notes, preferences)
                }
            }
        }
        //Deuxième thread démarré
        viewModelScope.launch {
            userPreferencesRepository.userPreference.collect{
                preferences = it
                _mainUiState.update {
                    MainUiState.Success(notes,preferences)
                }
            }
        }
    }
    fun deleteNote( note: Note){
        viewModelScope.launch {
            noteRepository.delete(note)
            _mainUiState.update {
                MainUiState.Empty
            }
        }
    }

}