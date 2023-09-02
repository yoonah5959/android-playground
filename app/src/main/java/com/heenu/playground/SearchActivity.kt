package com.heenu.playground

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.heenu.playground.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint


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
    }

    private fun initializeLayout() {
        with(binding) {
            searchInput.addTextChangedListener {
                viewModel.searchInput(it.toString())
            }

        }
    }
}