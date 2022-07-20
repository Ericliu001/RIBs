package com.uber.rib.compose.link

import motif.Expose

interface NavGraphObjects {
    fun dummyDestination(): DummyNavNode {
        return DummyNavNode()
    }

    @Expose
    fun rootDestination(dummyDestination: DummyNavNode): RootNavNode {
        return RootNavNode(dummyDestination)
    }

    @Expose
    fun mainDestination(rootDestination: RootNavNode) = MainNavNode(rootDestination)

    @Expose
    fun loggedInDestination(mainDestination: MainNavNode) = LoggedInNavNode(mainDestination)

    @Expose
    fun offGameDestination(loggedInDestination: LoggedInNavNode) =
        OffGameNavNode(loggedInDestination)
}