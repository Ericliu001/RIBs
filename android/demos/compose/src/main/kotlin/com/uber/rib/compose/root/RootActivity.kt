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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import com.ericliu.navigation.Destination
import com.uber.rib.compose.link.*
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import com.uber.rib.core.coroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import motif.Creatable
import motif.Expose
import motif.NoDependencies
import motif.ScopeFactory

class RootActivity : RibActivity() {
    private val uriChannel = Channel<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let {
            handleIntent(it)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *> {
        return ScopeFactory.create(Parent::class.java)
            .rootScope(this, findViewById(android.R.id.content), uriChannel)
            .router()
    }

    private fun handleIntent(intent: Intent) {
        intent.data?.let { uri ->
            coroutineScope.launch {
                uriChannel.send(uri)
            }
        }
    }

    @motif.Scope
    interface Parent : Creatable<NoDependencies> {
        fun rootScope(@Expose activity: RibActivity, parentViewGroup: ViewGroup, uriChannel: Channel<Uri>): RootScope
    }
}
