package com.norm.mykotlincoroutinesecrets

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import java.io.File

suspend fun createTempFile(context: Context) {
    val file = File(context.filesDir, "my-file.txt")
    try {
        writeLotsOfLines(file)
    } finally {
        println("Finally block entered!")
        withContext(NonCancellable){
            deleteFile(file)
        }
    }
}

private suspend fun deleteFile(file: File) {
    withContext(Dispatchers.IO) {
        file.delete()
        println("File deleted!")
    }
}

private suspend fun writeLotsOfLines(file: File) {
    withContext(Dispatchers.IO) {
        repeat(100_000) {
            file.appendText("Hello, Android!")
            ensureActive()
        }
    }
}