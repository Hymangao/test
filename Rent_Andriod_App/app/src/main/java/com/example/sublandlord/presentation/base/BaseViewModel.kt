package com.example.sublandlord.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T : UiState> : ViewModel() {

    private val _uiState = MutableStateFlow(createInitialState())
    val uiState: StateFlow<T> = _uiState.asStateFlow()

    protected abstract fun createInitialState(): T

    protected fun getCurrentState(): T {
        return _uiState.value
    }

    protected fun updateState(newState: T) {
        _uiState.value = newState
    }

    protected fun <R> launch(
        onSuccess: (R) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        block: suspend () -> R
    ) {
        viewModelScope.launch {
            try {
                val result = block()
                onSuccess(result)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}

interface UiState {
    val isLoading: Boolean
        get() = false
    val error: String?
        get() = null
}
