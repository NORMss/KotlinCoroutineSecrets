package com.norm.mykotlincoroutinesecrets

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch
import io.ktor.client.engine.okhttp.OkHttp


class PolingViewModel(context: Context) : ViewModel() {
    var isPolling by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            isPolling = true
            val text = fetchNetwork(HttpClient(OkHttp.create()))
            Log.d("MyLog", text)
            isPolling = false
        }
    }
}