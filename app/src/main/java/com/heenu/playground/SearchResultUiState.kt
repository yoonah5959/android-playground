package com.heenu.playground

sealed interface SearchResultUiState {
    data class Correct(val contentRes: Int = R.string.searchResultCorrect) : SearchResultUiState
    data class Wrong(val contentRes: Int = R.string.searchResultWrong) : SearchResultUiState
    object Empty : SearchResultUiState
    object LoadFailed : SearchResultUiState
    object Loading : SearchResultUiState
}