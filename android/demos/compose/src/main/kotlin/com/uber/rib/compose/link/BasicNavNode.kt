package com.ericliu.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

class BasicNavNode(val parent: NavNode) : NavNode {
    private val commandsChannel = Channel<NavCommand>()
    private val eventsChannel = Channel<NavEvent>()

    override suspend fun navigate(args: Map<String, String>) {
        parent.navigate(args)
        parent.requestChild(this)
    }

    override suspend fun requestChild(child: NavNode, args: Map<String, String>) {
        commandsChannel.send(NavCommand(child.javaClass, args))
        val receive = eventsChannel.receive()
        if (receive.placeholder == "placeholder") {
            // Log child attached.
        }
    }

    override fun canHandleBack(): Boolean {
        return false
    }

    override suspend fun back() {
        if (parent.canHandleBack()) {
            parent.navigate()
        }
    }

    override fun commandChannel(): ReceiveChannel<NavCommand> {
        return commandsChannel
    }

    override fun eventsChannel(): SendChannel<NavEvent> {
        return eventsChannel
    }
}