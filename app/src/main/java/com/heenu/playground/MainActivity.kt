package com.heenu.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heenu.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputButton.setOnClickListener {
            InputCoroutineActivity.onNewIntent(this).run {
                startActivity(this)
            }
        }

    }
}