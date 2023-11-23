package com.beasthub.greedycashandearn

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.beasthub.greedycashandearn.adapter.CoinsAdapter
import com.beasthub.greedycashandearn.databinding.FragmentRulesBinding
import com.beasthub.greedycashandearn.model.ApiViewModel
import com.beasthub.greedycashandearn.sharedprefsection.Constant
import com.example.networking.utils.ApiCallState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RulesFragment :AppCompatActivity() {

    lateinit var binding: FragmentRulesBinding
    val apiViewModel: ApiViewModel by viewModels()
    var game_type = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_rules)
        rulesData()
    }

    private fun rulesData() = CoroutineScope(Dispatchers.Main).launch {
        apiViewModel.greedyList(Constant.token, game_type.toString()).collectLatest {
            when (it) {
                is ApiCallState.Error -> {
                    Log.d("Error", "coinsList: ${it.error}")
                }
                ApiCallState.Loading -> {}
                is ApiCallState.Success -> {
                    Log.d("GameRules", "coinsList: ${it.data.gameRules}")
                    if (it.data!!.status.equals("true", ignoreCase = true)) {
                            it.data.gameRules.apply {
                                val htmlContent = rules
                                binding.rulesData.text  = android.text.Html.fromHtml(htmlContent)
                            }

                    }
                }
            }
        }
    }

}