@file:OptIn(InternalAPI::class)

package com.norm.mykotlincoroutinesecrets

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.InternalAPI
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration.Companion.seconds

suspend fun fetchNetwork(client: HttpClient): String {
    val uncensored = ""
    while (true) {
        try {
            println("Poling network resource...")

            val posts = client.get(
                urlString = "https://jsonplaceholder.typicode.com/posts"
            ) {
                parameter("text", uncensored)
            }
            println("Received posts!")

            delay(30.seconds)
        } catch (e: Exception) {

//            if (e is CancellationException) throw e

            coroutineContext.ensureActive()

            println("Oops, something went wrong, make sure you're connected to the internet!")
//            throw e
        }
    }
    return uncensored
}