package com.norm.mykotlincoroutinesecrets

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.io.ByteArrayOutputStream

class ImageReader(
    private val context: Context,
    private val dispatcher: DispatcherProvider,
) {
    suspend fun readImageFromUri(contentUri: Uri) {
        return withContext(dispatcher.io) {
            context.contentResolver.openInputStream(contentUri)?.use { inputStream ->
                ByteArrayOutputStream().use { outputStream ->
                    var byte = inputStream.read()
                    while (byte != -1) {
                        outputStream.write(byte)
                        byte = inputStream.read()

//                        ensureActive()
                        yield()
                    }
                    outputStream.toByteArray()
                }
            } ?: byteArrayOf()
        }
    }
}

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}

object StandardDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}

//class TestDispatcherProvider(
//    val testDispatcher: TestDispatcher
//) : DispatcherProvider {
//    override val main: CoroutineDispatcher
//        get() = testDispatcher
//    override val io: CoroutineDispatcher
//        get() = testDispatcher
//    override val default: CoroutineDispatcher
//        get() = testDispatcher
//}