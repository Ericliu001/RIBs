package com.uber.rib.compose.link

import com.ericliu.navigation.BasicDestination
import com.ericliu.navigation.Destination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

class DummyDestination: Destination  {
    override suspend fun navigate() {
        /* no-op */
        return
    }

    override suspend fun requestChild(child: Destination) {
        /* no-op */
        return
    }

    override suspend fun back() {
        /* no-op */
    }

    override fun commandChannel(): ReceiveChannel<String> {
        return Channel(0)
    }

    override fun updatesChannel(): SendChannel<String> {
        return Channel(0)
    }
}