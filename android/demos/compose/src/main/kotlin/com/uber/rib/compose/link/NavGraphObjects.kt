package com.uber.rib.compose.link

import motif.Expose

interface NavGraphObjects {
    fun dummyNavNode(): DummyNavNode {
        return DummyNavNode()
    }

    @Expose
    fun rootNavNode(dummyNavNode: DummyNavNode): RootNavNode {
        return RootNavNode(dummyNavNode)
    }

    @Expose
    fun mainNavNode(rootNavNode: RootNavNode) = MainNavNode(rootNavNode)

    @Expose
    fun loggedInNavNode(mainNavNode: MainNavNode) = LoggedInNavNode(mainNavNode)

    @Expose
    fun offGameNavNode(loggedInNavNode: LoggedInNavNode) =
        OffGameNavNode(loggedInNavNode)
}