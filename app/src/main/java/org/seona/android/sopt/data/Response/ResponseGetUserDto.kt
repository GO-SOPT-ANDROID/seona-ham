package org.seona.android.sopt.data.Response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserDto(
    @SerialName("page")
    val status: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_page: Int,
    @SerialName("data")
    val data: List<UserData>,
    @SerialName("support")
    val support: UserSupport,
) {
    @Serializable
    data class UserData(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val first_name: String,
        @SerialName("last_name")
        val last_name: String,
        @SerialName("avatar")
        val avatar: String
    )

    @Serializable
    data class UserSupport(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )
}