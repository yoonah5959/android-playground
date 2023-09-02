package com.heenu.playground

sealed interface SearchResultUiState {
    data class Correct(val content: String) : SearchResultUiState
    data class Wrong(val content: String) : SearchResultUiState
    object LoadFailed : SearchResultUiState
    object Loading : SearchResultUiState
}