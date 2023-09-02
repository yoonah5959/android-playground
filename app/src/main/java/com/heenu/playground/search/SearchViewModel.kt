package com.heenu.playground.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.heenu.common.network.Result
import com.heenu.common.network.asResult
import com.heenu.playground.SearchResultUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
@OptIn(FlowPreview::class)
class SearchViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val AVAILABLE_RESULT = "우웅"
    }

    private val searchQuery = MutableStateFlow<String>("")

    val searchResult: Flow<SearchResultUiState> =
        searchQuery
            .debounce(500)
            .filter { it.trim().isEmpty().not() }
            .flatMapLatest {
                getSearchContent(it)
                    .asResult()
                    .map { result ->
                        when (result) {
                            Result.Loading -> SearchResultUiState.Loading
                            is Result.Success -> SearchResultUiState.Success(result.data)
                            else -> {
                                SearchResultUiState.LoadFailed
                            }
                        }
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchResultUiState.Loading,
            )

    fun onSearchQueryChanged(newSearchQuery: String) {
        viewModelScope.launch {
            searchQuery.emit(newSearchQuery)
        }
    }

    private suspend fun getSearchContent(query: String): Flow<String> {
        // repository에서 데이터를 가져왔다고 가정
        delay(500)

        return if (query == AVAILABLE_RESULT) {
            flowOf("치킨")
        } else {
            flowOf("땡! 원하는 답을 해주십시오.")
        }
    }
}