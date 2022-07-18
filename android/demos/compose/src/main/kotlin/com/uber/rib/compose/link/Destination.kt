package com.ericliu.navigation

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

interface Destination {
    suspend fun navigate()
    suspend fun requestChild(child: Destination)
    suspend fun back()

    fun commandChannel(): ReceiveChannel<String>
    fun updatesChannel(): SendChannel<String>
}