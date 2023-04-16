package ru.alexbur.feature.authorization.data.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthorizationRequest(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String
)