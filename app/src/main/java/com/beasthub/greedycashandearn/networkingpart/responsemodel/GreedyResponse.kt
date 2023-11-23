package net.saawan.www.networkingpart.responsemodel


import com.google.gson.annotations.SerializedName

data class GreedyResponse(
    @SerializedName("coin_data")
    val coinData: List<CoinData>,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("game_rules")
    val gameRules: GameRules,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: String
) {
    data class CoinData(
        @SerializedName("coin")
        val coin: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("game_type")
        val gameType: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("status")
        val status: String
    )

    data class Data(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("game_type")
        val gameType: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("spin_number")
        val spinNumber: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("times")
        val times: String,
        @SerializedName("title")
        val title: String
    )

    data class GameRules(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("game_type")
        val gameType: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("rules")
        val rules: String,
        @SerializedName("status")
        val status: String
    )
}