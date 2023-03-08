package ru.alexbur.core.domain.erro_handler

interface ErrorHandler {
    fun handleError(error: Throwable): String
}