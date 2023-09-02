package com.heenu.playground

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.heenu.playground.databinding.ActivityInputCoroutineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber


@AndroidEntryPoint
class InputCoroutineActivity : AppCompatActivity() {

    companion object {
        fun onNewIntent(context: Context): Intent {
            return Intent(context, InputCoroutineActivity::class.java)
        }
    }

    private lateinit var binding: ActivityInputCoroutineBinding

    private val viewModel: InputCoroutineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeLayout()
    }

    private fun initializeLayout() {
        with(binding) {
            idInput.addTextChangedListener {
                viewModel.inputId(it.toString())
            }

            pwInput.addTextChangedListener {
                viewModel.inputPw(it.toString())
            }
        }
    }
}