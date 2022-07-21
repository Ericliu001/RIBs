package com.ericliu.navigation

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

interface NavNode {
    suspend fun navigate(args: Map<String, String> = emptyMap())
    suspend fun requestChild(child: NavNode, args: Map<String, String> = emptyMap())
    fun canHandleBack(): Boolean
    suspend fun back()

    fun commandChannel(): ReceiveChannel<NavCommand>
    fun eventsChannel(): SendChannel<NavEvent>
}

data class NavCommand(val destinationClass: Class<NavNode>, val args: Map<String, String>)

data class NavEvent(val placeholder: String)