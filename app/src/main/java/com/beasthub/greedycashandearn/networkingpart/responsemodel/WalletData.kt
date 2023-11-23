package net.saawan.www.networkingpart.responsemodel


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class WalletData(
    @SerializedName("data")
    @Expose
    val `data`: Data,
    @SerializedName("PushNotification")
    @Expose
    val pushNotification: PushNotification,
    @SerializedName("PrivacySetting")
    @Expose
    val privacySetting: PrivacySetting,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("msg")
    @Expose
    val msg: String
) {
    data class Data(
        @SerializedName("id")
        @Expose
        val id: String,
        @SerializedName("nick_name")
        @Expose
        val nickName: String,
        @SerializedName("first_name")
        @Expose
        val firstName: String,
        @SerializedName("last_name")
        @Expose
        val lastName: String,
        @SerializedName("gender")
        @Expose
        val gender: String,
        @SerializedName("bio")
        @Expose
        val bio: String,
        @SerializedName("website")
        @Expose
        val website: String,
        @SerializedName("dob")
        @Expose
        val dob: String,
        @SerializedName("social_id")
        @Expose
        val socialId: String,
        @SerializedName("language_id")
        @Expose
        val languageId: Any,
        @SerializedName("email")
        @Expose
        val email: String,
        @SerializedName("country_code")
        @Expose
        val countryCode: String,
        @SerializedName("phone")
        @Expose
        val phone: String,
        @SerializedName("password")
        @Expose
        val password: String,
        @SerializedName("profile_pic")
        @Expose
        val profilePic: String,
        @SerializedName("profile_pic_small")
        @Expose
        val profilePicSmall: String,
        @SerializedName("role")
        @Expose
        val role: String,
        @SerializedName("username")
        @Expose
        val username: String,
        @SerializedName("social")
        @Expose
        val social: String,
        @SerializedName("device_token")
        @Expose
        val deviceToken: String,
        @SerializedName("token")
        @Expose
        val token: String,
        @SerializedName("active")
        @Expose
        val active: String,
        @SerializedName("lat")
        @Expose
        val lat: String,
        @SerializedName("long")
        @Expose
        val long: String,
        @SerializedName("online")
        @Expose
        val online: String,
        @SerializedName("video_streaming")
        @Expose
        val videoStreaming: String,
        @SerializedName("verification_code")
        @Expose
        val verificationCode: String,
        @SerializedName("verified")
        @Expose
        val verified: String,
        @SerializedName("auth_token")
        @Expose
        val authToken: String,
        @SerializedName("version")
        @Expose
        val version: String,
        @SerializedName("device")
        @Expose
        val device: String,
        @SerializedName("ip")
        @Expose
        val ip: String,
        @SerializedName("city")
        @Expose
        val city: String,
        @SerializedName("country")
        @Expose
        val country: String,
        @SerializedName("city_id")
        @Expose
        val cityId: String,
        @SerializedName("state_id")
        @Expose
        val stateId: String,
        @SerializedName("country_id")
        @Expose
        val countryId: String,
        @SerializedName("wallet")
        @Expose
        val wallet: String,
        @SerializedName("target_wallet")
        @Expose
        val targetWallet: String,
        @SerializedName("paypal")
        @Expose
        val paypal: String,
        @SerializedName("address")
        @Expose
        val address: String,
        @SerializedName("reset_wallet_datetime")
        @Expose
        val resetWalletDatetime: String,
        @SerializedName("fb_id")
        @Expose
        val fbId: String,
        @SerializedName("linkedin_id")
        @Expose
        val linkedinId: String,
        @SerializedName("session_id")
        @Expose
        val sessionId: String,
        @SerializedName("refer_code")
        @Expose
        val referCode: String,
        @SerializedName("profile_complete")
        @Expose
        val profileComplete: String,
        @SerializedName("level")
        @Expose
        val level: String,
        @SerializedName("coin_level")
        @Expose
        val coinLevel: String,
        @SerializedName("coin_exp")
        @Expose
        val coinExp: String,
        @SerializedName("exp_points")
        @Expose
        val expPoints: String,
        @SerializedName("is_vip")
        @Expose
        val isVip: String,
        @SerializedName("created")
        @Expose
        val created: String,
        @SerializedName("image")
        @Expose
        val image: String,
        @SerializedName("level_image")
        @Expose
        val levelImage: String,
        @SerializedName("coin_level_image")
        @Expose
        val coinLevelImage: String,
        @SerializedName("vip_image")
        @Expose
        val vipImage: String,
        @SerializedName("font_family")
        @Expose
        val fontFamily: String,
        @SerializedName("text_buble")
        @Expose
        val textBuble: String,
        @SerializedName("wall_papers")
        @Expose
        val wallPapers: String,
        @SerializedName("entrance_image")
        @Expose
        val entranceImage: String,
        @SerializedName("prototype_entrance_image")
        @Expose
        val prototypeEntranceImage: String,
        @SerializedName("ribbon_image")
        @Expose
        val ribbonImage: String,
        @SerializedName("followers_count")
        @Expose
        val followersCount: Int,
        @SerializedName("following_count")
        @Expose
        val followingCount: Int,
        @SerializedName("frame_image")
        @Expose
        val frameImage: String,
        @SerializedName("vistor_count")
        @Expose
        val vistorCount: Int,
        @SerializedName("receive_gift_count")
        @Expose
        val receiveGiftCount: Int,
        @SerializedName("send_gift_count")
        @Expose
        val sendGiftCount: Int,
        @SerializedName("total_gift_count")
        @Expose
        val totalGiftCount: Int,
        @SerializedName("receive_gift_sum")
        @Expose
        val receiveGiftSum: String,
        @SerializedName("send_gift_sum")
        @Expose
        val sendGiftSum: String,
        @SerializedName("agency_id")
        @Expose
        val agencyId: String,
        @SerializedName("agency_role")
        @Expose
        val agencyRole: String,
        @SerializedName("kyc_status")
        @Expose
        val kycStatus: Int
    )

    data class PushNotification(
        @SerializedName("id")
        @Expose
        val id: String,
        @SerializedName("likes")
        @Expose
        val likes: String,
        @SerializedName("comments")
        @Expose
        val comments: String,
        @SerializedName("new_followers")
        @Expose
        val newFollowers: String,
        @SerializedName("mentions")
        @Expose
        val mentions: String,
        @SerializedName("direct_messages")
        @Expose
        val directMessages: String,
        @SerializedName("video_updates")
        @Expose
        val videoUpdates: String
    )

    data class PrivacySetting(
        @SerializedName("id")
        @Expose
        val id: String,
        @SerializedName("videos_download")
        @Expose
        val videosDownload: String,
        @SerializedName("direct_message")
        @Expose
        val directMessage: String,
        @SerializedName("videos_repost")
        @Expose
        val videosRepost: String,
        @SerializedName("duet")
        @Expose
        val duet: String,
        @SerializedName("liked_videos")
        @Expose
        val likedVideos: String,
        @SerializedName("video_comment")
        @Expose
        val videoComment: String
    )
}