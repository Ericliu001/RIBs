package com.uber.rib.compose.link

import com.ericliu.navigation.NavNode
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

class DummyNavNode: NavNode  {
    override suspend fun navigate() {
        /* no-op */
        return
    }

    override suspend fun requestChild(child: NavNode) {
        /* no-op */
        return
    }

    override suspend fun back() {
        /* no-op */
    }

    override fun commandChannel(): ReceiveChannel<String> {
        return Channel(0)
    }

    override fun eventsChannel(): SendChannel<String> {
        return Channel(0)
    }
}