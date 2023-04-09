package ru.alexbur.core.domain.error_handler

interface ErrorHandler {
    fun handleError(error: Throwable): String
}