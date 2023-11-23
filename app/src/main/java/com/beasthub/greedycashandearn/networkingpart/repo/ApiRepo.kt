package com.example.networking.repo

import com.example.networking.retrofitdi.ApiService
import com.example.networking.utils.result
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

class ApiRepo @Inject constructor(private val apiService: ApiService) {

   suspend  fun greedyList(auth_token: String, game_type:String) = result {
        apiService.greedyList(auth_token, game_type)
    }
    suspend  fun gameList(auth_token: String) = result {
        apiService.gameList(auth_token)
    }
    suspend  fun walletData(auth_token: String) = result {
        apiService.getWalletAmount(auth_token)
    }
}