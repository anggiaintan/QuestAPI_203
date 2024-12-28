package com.example.api.ui.viewmodel

import model.Mahasiswa

sealed class HomeUiState {
    data class Success(val mahasiswaList: List<Mahasiswa>) : HomeUiState ()
    object Error : HomeUiState ()
    object Loading : HomeUiState ()
}