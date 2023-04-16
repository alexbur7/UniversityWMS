package ru.alexbur.core.di.extentions

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun <T : ViewModel> daggerViewModel(
    viewModel: Class<T>,
    key: String? = null,
    viewModelInstanceCreator: () -> T
): T = viewModel(
    modelClass = viewModel,
    key = key,
    factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModelInstanceCreator() as T
        }
    }
)