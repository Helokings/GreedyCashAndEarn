package com.example.networking.retrofitdi

import net.saawan.www.networkingpart.responsemodel.GameResponse
import net.saawan.www.networkingpart.responsemodel.GreedyResponse
import net.saawan.www.networkingpart.responsemodel.WalletData
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @FormUrlEncoded
    @POST("greedy_game/game_list_details")
   suspend fun greedyList(
        @Field("auth_token") auth_token: String,
        @Field("game_type") game_type: String
    ): Response<GreedyResponse>

   @FormUrlEncoded
    @POST("greedy_game/game_list")
   suspend fun gameList(
        @Field("auth_token") auth_token: String): Response<GameResponse>

    @FormUrlEncoded
    @POST("Api_latest/userdata")
   suspend fun getWalletAmount(@Field("auth_token") auth_token: String): Response<WalletData>

}