package com.heenu.playground.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heenu.playground.SearchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class SearchViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val CORRECT_QUERY = "우웅"
    }

    private val searchQuery = MutableStateFlow<String>("")

    val searchResult: StateFlow<SearchResultUiState> =
        searchQuery
            .debounce(250)
            .flatMapConcat { query ->
                flow {
                    if (query.trim().isEmpty()) {
                        emit(SearchResultUiState.Empty)
                    } else {
                        emit(SearchResultUiState.Loading)
                        delay(1000)
                        if (query == CORRECT_QUERY)
                            emit(SearchResultUiState.Correct())
                        else
                            emit(SearchResultUiState.Wrong())
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchResultUiState.Empty,
            )

    fun onSearchQueryChanged(newSearchQuery: String) {
        viewModelScope.launch {
            searchQuery.emit(newSearchQuery)
        }
    }
}