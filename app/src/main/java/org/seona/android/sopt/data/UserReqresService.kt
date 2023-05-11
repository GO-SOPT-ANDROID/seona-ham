package org.seona.android.sopt.data

import org.seona.android.sopt.data.Response.ResponseGetUserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserReqresService {
    @GET("api/users")
    fun getUsers(
        @Query("page") page : Int
    ): Call<ResponseGetUserDto>
}