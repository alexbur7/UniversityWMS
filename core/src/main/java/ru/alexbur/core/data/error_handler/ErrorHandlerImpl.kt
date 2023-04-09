package ru.alexbur.core.data.error_handler

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import ru.alexbur.uikit.R
import ru.alexbur.core.domain.error_handler.ErrorHandler
import java.net.ConnectException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
): ErrorHandler {

    override fun handleError(error: Throwable): String {
        return when (error) {
            is HttpException -> {
                handleHttpError(error = error)
            }
            is ConnectException -> {
                context.getString(R.string.internet_error)
            }
            else -> context.getString(R.string.unknown_error)
        }
    }

    private fun handleHttpError(error: HttpException): String {
        return when (error.code()) {
            400 -> context.getString(R.string.unknown_server_error)
            401 -> context.getString(R.string.auth_error)
            406 -> context.getString(R.string.validaton_error)
            else -> error.message()
        }
    }
}