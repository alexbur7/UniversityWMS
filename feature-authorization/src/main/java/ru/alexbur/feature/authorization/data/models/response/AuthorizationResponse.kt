package ru.alexbur.feature.authorization.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.alexbur.feature.authorization.domain.models.AuthorizationToken

@Serializable
class AuthorizationResponse(
    @SerialName("token")
    val token: String
) {

    companion object {
        fun toModel(response: AuthorizationResponse): AuthorizationToken {
            return AuthorizationToken(
                accessToken = response.token
            )
        }
    }
}