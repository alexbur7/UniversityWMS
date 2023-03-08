package ru.alexbur.core.data.providers

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.alexbur.core.domain.providers.StringProvider
import javax.inject.Inject

class StringProvidersImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : StringProvider {
    override fun getString(resId: Int, vararg argument: Any): String {
        return context.getString(resId, argument)
    }
}