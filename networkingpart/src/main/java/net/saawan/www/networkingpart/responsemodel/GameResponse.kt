package net.saawan.www.networkingpart.responsemodel


import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("game_type")
        val gameType: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("images")
        val images: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("title")
        val title: String
    )
}