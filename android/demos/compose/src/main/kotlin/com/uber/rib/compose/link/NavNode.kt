package com.ericliu.navigation

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

interface NavNode {
    suspend fun navigate()
    suspend fun requestChild(child: NavNode)
    suspend fun back()

    fun commandChannel(): ReceiveChannel<String>
    fun eventsChannel(): SendChannel<String>
}