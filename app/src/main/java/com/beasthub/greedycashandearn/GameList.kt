package com.beasthub.greedycashandearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.beasthub.greedycashandearn.databinding.ActivityGameListBinding
import com.beasthub.greedycashandearn.model.ApiViewModel
import com.beasthub.greedycashandearn.sharedprefsection.Constant
import com.example.networking.utils.ApiCallState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameList : AppCompatActivity() {
    lateinit var binding: ActivityGameListBinding
    val apiViewModel: ApiViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game_list)
        gameList()
        val intent = Intent(this@GameList, MainActivity::class.java)
        intent.putExtra("title", title)
        startActivity(intent)
    }

    private fun gameList() = CoroutineScope(Dispatchers.Main).launch {
        apiViewModel.gameList(Constant.token).collectLatest {
            when (it) {
                is ApiCallState.Error -> {
                    Log.d("Error", "coinsList: ${it.error}")
                }
                ApiCallState.Loading -> {}
                is ApiCallState.Success -> {
                    Log.d("GameRules", "coinsList: ${it.data.data}")
                    if (it.data!!.status.equals("true", ignoreCase = true)) {
                        it.data.data.apply {
                            binding.rulesData.text = title
                            binding.rulesData.setOnClickListener {
                                val intent = Intent(this@GameList, MainActivity::class.java)
                                intent.putExtra("title", title)
                                startActivity(intent)
                            }

                        }
                    }
                }
            }
        }
    }
}