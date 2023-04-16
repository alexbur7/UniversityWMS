package ru.alexbur.feature.profile.data.api

import retrofit2.http.GET
import ru.alexbur.feature.profile.data.models.response.ProfileResponse

interface ProfileApi {

    @GET("profile")
    suspend fun getProfile(): ProfileResponse
}