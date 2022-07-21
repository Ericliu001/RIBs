package com.uber.rib.compose.link

import com.ericliu.navigation.NavCommand
import com.ericliu.navigation.NavEvent
import com.ericliu.navigation.NavNode
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

class DummyNavNode : NavNode {
    override suspend fun navigate(args: Map<String, String>) {
        /* no-op */
        return
    }

    override suspend fun requestChild(child: NavNode, args: Map<String, String>) {
        /* no-op */
        return
    }

    override fun canHandleBack(): Boolean {
        return false
    }

    override suspend fun back() {
        /* no-op */
    }

    override fun commandChannel(): ReceiveChannel<NavCommand> {
        return Channel(0)
    }

    override fun eventsChannel(): SendChannel<NavEvent> {
        return Channel(0)
    }
}