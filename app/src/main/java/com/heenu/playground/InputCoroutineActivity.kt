package com.heenu.playground

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heenu.playground.databinding.ActivityInputCoroutineBinding


class InputCoroutineActivity : AppCompatActivity() {

    companion object {
        fun onNewIntent(context: Context): Intent {
            return Intent(context, InputCoroutineActivity::class.java)
        }
    }

    private lateinit var binding: ActivityInputCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}