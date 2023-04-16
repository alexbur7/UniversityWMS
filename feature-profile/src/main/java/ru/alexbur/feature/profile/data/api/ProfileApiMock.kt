package ru.alexbur.feature.profile.data.api

import kotlinx.coroutines.delay
import ru.alexbur.feature.profile.data.models.response.ProfileResponse

class ProfileApiMock : ProfileApi {

    override suspend fun getProfile(): ProfileResponse {
        delay(1500L)
        return ProfileResponse(
            avatar = "https://www.re-sourcepartners.com/wp-content/uploads/2020/04/physical-inventory-banner.jpg",
            name = "Азиз Суроев",
            jobTitle = "Складской работник"
        )
    }
}