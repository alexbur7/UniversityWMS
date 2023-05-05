package ru.alexbur.core.data.mock_db

import kotlinx.coroutines.flow.MutableStateFlow

object MockDB {
    val scannerData = MutableStateFlow<List<Pair<Long, String>>>(emptyList())
}