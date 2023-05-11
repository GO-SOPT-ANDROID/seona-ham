package org.seona.android.sopt.data.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String,
    @SerialName("skill")
    val mbti: String,
)
