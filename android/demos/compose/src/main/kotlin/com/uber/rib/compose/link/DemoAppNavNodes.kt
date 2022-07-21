package com.uber.rib.compose.link

import com.ericliu.navigation.BasicNavNode
import com.ericliu.navigation.NavNode

class RootNavNode(parent: NavNode) : NavNode by BasicNavNode(parent)

class MainNavNode(parent: NavNode) : NavNode by BasicNavNode(parent)

class LoggedInNavNode(parent: NavNode) : NavNode by BasicNavNode(parent) {
    override fun canHandleBack(): Boolean {
        return true
    }
}

class OffGameNavNode(parent: NavNode) : NavNode by BasicNavNode(parent)

