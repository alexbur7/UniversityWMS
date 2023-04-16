package ru.alexbur.feature.profile.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.alexbur.feature.profile.domain.models.Profile

@Serializable
class ProfileResponse(
    @SerialName("avatar")
    val avatar: String,
    @SerialName("name")
    val name: String,
    @SerialName("job_title")
    val jobTitle: String
) {

    companion object {
        fun toModel(response: ProfileResponse): Profile {
            return Profile(
                avatar = response.avatar,
                name = response.name,
                jobTitle = response.jobTitle
            )
        }
    }
}