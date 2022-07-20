package com.ericliu.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

open class BasicNavNode(val parent: NavNode) : NavNode {
    private val commandsChannel = Channel<String>()
    private val eventsChannel = Channel<String>()

    override suspend fun navigate() {
        parent.navigate()
        parent.requestChild(this)
    }

    override suspend fun requestChild(child: NavNode) {
        commandsChannel.send(child.javaClass.canonicalName)
        val receive = eventsChannel.receive()
        if (receive == child.javaClass.canonicalName) {
            // Log child attached.
        }
    }

    override suspend fun back() {
        parent.navigate()
    }

    override fun commandChannel(): ReceiveChannel<String> {
        return commandsChannel
    }

    override fun eventsChannel(): SendChannel<String> {
        return eventsChannel
    }
}