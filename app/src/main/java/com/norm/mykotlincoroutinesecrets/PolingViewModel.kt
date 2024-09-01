package com.norm.mykotlincoroutinesecrets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class PolingViewModel : ViewModel() {
    var isPolling by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            isPolling = true
            fetchNetwork(HttpClient())
            isPolling = false
        }
    }
}