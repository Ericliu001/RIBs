package com.ericliu.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

open class BasicDestination(val parent: Destination) : Destination {
    private val commandsChannel = Channel<String>()
    private val updatesChannel = Channel<String>()

    override suspend fun navigate() {
        parent.navigate()
        parent.requestChild(this)
    }

    override suspend fun requestChild(child: Destination) {
        commandsChannel.send(child.javaClass.canonicalName)
        val receive = updatesChannel.receive()
        if (receive == child.javaClass.canonicalName) {
            // Log child attached.
        }
    }

    override suspend fun back() {
        TODO("Not yet implemented")
    }

    override fun commandChannel(): ReceiveChannel<String> {
        return commandsChannel
    }

    override fun updatesChannel(): SendChannel<String> {
        return updatesChannel
    }
}