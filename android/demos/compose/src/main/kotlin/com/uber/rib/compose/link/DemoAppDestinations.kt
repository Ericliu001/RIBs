package com.uber.rib.compose.link

import com.ericliu.navigation.BasicNavNode
import com.ericliu.navigation.NavNode

class LoggedInNavNode(parent: NavNode) : NavNode by BasicNavNode(parent)

class MainNavNode(parent: NavNode) : NavNode by BasicNavNode(parent)

class OffGameNavNode(parent: NavNode) : NavNode by BasicNavNode(parent)

class RootNavNode(parent: NavNode) : NavNode by BasicNavNode(parent)