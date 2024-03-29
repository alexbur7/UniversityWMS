package ru.alexbur.core.data.error_handler

import android.content.Context
import retrofit2.HttpException
import ru.alexbur.core.domain.error_handler.ErrorHandler
import ru.alexbur.uikit.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(
    private val context: Context
) : ErrorHandler {

    override fun handleError(error: Throwable): String {
        return when (error) {
            is HttpException -> {
                handleHttpError(error = error)
            }
            is ConnectException -> {
                context.getString(R.string.internet_error)
            }
            is SocketTimeoutException -> {
                context.getString(R.string.socket_timeout_error)
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