package ca.qc.cstj.localdatasource.presentation.main

import ca.qc.cstj.localdatasource.domain.models.Note

sealed class MainUiState {
    object Empty : MainUiState()
    class  Success(val notes: List<Note>): MainUiState()

}