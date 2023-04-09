package ru.alexbur.feature.authorization.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.alexbur.feature.authorization.data.models.request.AuthorizationRequest
import ru.alexbur.feature.authorization.data.models.response.AuthorizationResponse

interface AuthorizationApi {

    @POST("auth")
    suspend fun auth(@Body request: AuthorizationRequest): AuthorizationResponse
}