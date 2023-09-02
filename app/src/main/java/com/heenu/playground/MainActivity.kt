package com.heenu.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heenu.playground.databinding.ActivityMainBinding
import kotlin.contracts.ExperimentalContracts

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    @OptIn(ExperimentalContracts::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputButton.setOnClickListener {
            SearchActivity.onNewIntent(this).run {
                startActivity(this)
            }
        }

    }
}