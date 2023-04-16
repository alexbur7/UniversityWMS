package ru.alexbur.feature.authorization.data.api

import ru.alexbur.feature.authorization.data.models.request.AuthorizationRequest
import ru.alexbur.feature.authorization.data.models.response.AuthorizationResponse

class AuthorizationApiMock : AuthorizationApi {

    override suspend fun auth(request: AuthorizationRequest): AuthorizationResponse {
        return AuthorizationResponse(token = "ABFQWKNK12512BFAJFAGAWN8AWKPWIINQPNWIN")
    }
}