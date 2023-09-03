package com.heenu.playground.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.heenu.playground.SearchResultUiState
import com.heenu.playground.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    companion object {
        fun onNewIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    private lateinit var binding: ActivitySearchBinding

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeLayout()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchResult
                    .collectLatest { uiState ->
                        with(binding) {
                            when (uiState) {
                                is SearchResultUiState.Correct -> {
                                    progressBar.visibility = View.GONE
                                    searchResult.setText(uiState.contentRes)
                                    searchResultImage.visibility = View.VISIBLE
                                }

                                is SearchResultUiState.Wrong -> {
                                    progressBar.visibility = View.GONE
                                    searchResult.setText(uiState.contentRes)
                                    searchResultImage.visibility = View.GONE
                                }

                                SearchResultUiState.Empty -> {
                                    progressBar.visibility = View.GONE
                                    searchResult.text = ""
                                    searchResultImage.visibility = View.GONE
                                }

                                SearchResultUiState.Loading -> {
                                    progressBar.visibility = View.VISIBLE
                                }

                                else -> {}
                            }
                        }

                    }
            }
        }
    }

    private fun initializeLayout() {
        with(binding) {
            searchInput.addTextChangedListener {
                viewModel.onSearchQueryChanged(it.toString())
            }
        }
    }
}