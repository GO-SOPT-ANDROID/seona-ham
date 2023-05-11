package org.seona.android.sopt.data.Request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSigninDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String,
)
