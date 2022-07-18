package com.uber.rib.compose.link

import com.ericliu.navigation.BasicDestination
import com.ericliu.navigation.Destination

class RootDestination(parent: Destination) : Destination by BasicDestination(parent) {
}