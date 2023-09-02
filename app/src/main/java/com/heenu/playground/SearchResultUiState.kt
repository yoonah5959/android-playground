package com.heenu.playground

sealed interface SearchResultUiState {
    data class Success(val content: String) : SearchResultUiState
    object LoadFailed : SearchResultUiState
    object Loading : SearchResultUiState
}