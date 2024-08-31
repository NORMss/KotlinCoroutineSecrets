package com.norm.mykotlincoroutinesecrets

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

suspend fun fetchNetwork(client: HttpClient) {
    while (true) {
        try {
            println("Poling network resource...")

            val posts = client.get(
                urlString = "https://jsonplaceholder.typicode.com/posts"
            )

            println("Received posts!")

            delay(30.seconds)
        } catch (e: Exception) {
            println("Oops, something went wrong, make sure you're connected to the internet!")
        }
    }
}