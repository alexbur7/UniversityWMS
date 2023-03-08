package ru.alexbur.core.domain.providers

import androidx.annotation.StringRes

interface StringProvider {

    fun getString(@StringRes resId: Int, vararg argument: Any): String
}