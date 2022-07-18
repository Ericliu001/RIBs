package com.uber.rib.compose.link

import motif.Expose

interface NavGraphObjects {
    fun dummyDestination(): DummyDestination {
        return DummyDestination()
    }

    @Expose
    fun rootDestination(dummyDestination: DummyDestination): RootDestination {
        return RootDestination(dummyDestination)
    }

    @Expose
    fun mainDestination(rootDestination: RootDestination) = MainDestination(rootDestination)

    @Expose
    fun loggedInDestination(mainDestination: MainDestination) = LoggedInDestination(mainDestination)

    @Expose
    fun offGameDestination(loggedInDestination: LoggedInDestination) =
        OffGameDestination(loggedInDestination)
}