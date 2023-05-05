package ru.alexbur.core.data.providers

import android.content.Context
import ru.alexbur.core.domain.providers.StringProvider
import javax.inject.Inject

class StringProvidersImpl @Inject constructor(
    private val context: Context
) : StringProvider {
    override fun getString(resId: Int, vararg argument: Any): String {
        return context.getString(resId, argument.toString())
    }
}