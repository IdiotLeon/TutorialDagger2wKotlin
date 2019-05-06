package com.idiotleon.tutorialdagger2withkotlin.components

import dagger.Component

@Component
interface RootFactory {
    fun presenterFactory(): PresenterFactory.Builder
}

object App {
    val rootFactor = DaggerRootFactory.create()
}