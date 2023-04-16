package ru.alexbur.feature.profile.domain.repository

import ru.alexbur.feature.profile.domain.models.Profile

interface ProfileRepository {

    suspend fun getProfile(): Profile
}