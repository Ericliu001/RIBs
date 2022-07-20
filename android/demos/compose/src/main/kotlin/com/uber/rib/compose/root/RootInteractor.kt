/*
 * Copyright (C) 2021. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uber.rib.compose.root

import android.net.Uri
import com.uber.rib.compose.link.OffGameNavNode
import com.uber.rib.compose.link.RootNavNode
import com.uber.rib.core.BasicInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RootInteractor(
    presenter: EmptyPresenter,
    val rootDestination: RootNavNode,
    val uriChannel: Channel<Uri>,
    val offGameDestination: OffGameNavNode
) :
    BasicInteractor<EmptyPresenter, RootRouter>(presenter) {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        coroutineScope.launch {
            val command = rootDestination
                .commandChannel()
                .receive()

            router.attachMain()
            rootDestination.eventsChannel().send("")
        }

        coroutineScope.launch {
            val receiveAsFlow = uriChannel.receiveAsFlow().collect { uri ->

                offGameDestination.navigate()
            }
        }
    }
}
