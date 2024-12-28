package com.example.api.ui.viewmodel

data class InsertUiState (
    val insertUiEvent: InsertUiEvent = InsertUiEvent(),
)

data class InsertUiEvent (
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)