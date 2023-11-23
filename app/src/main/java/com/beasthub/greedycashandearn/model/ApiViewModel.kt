package com.beasthub.greedycashandearn.model

import androidx.lifecycle.ViewModel
import com.example.networking.repo.ApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val apiRepo: ApiRepo) : ViewModel() {

   suspend fun greedyList(auth_token:String,
    game_type:String) =
        apiRepo.greedyList(auth_token,game_type)

    suspend fun gameList(auth_token:String) =
        apiRepo.gameList(auth_token)

    suspend fun walletData(auth_token:String) =
        apiRepo.walletData(auth_token)
}