package com.idiotleon.tutorialdagger2withkotlin.essentials

import com.idiotleon.tutorialdagger2withkotlin.BuildConfig
import com.idiotleon.tutorialdagger2withkotlin.extra.DEBUG_ENDPOINT
import com.idiotleon.tutorialdagger2withkotlin.extra.PROD_ENDPOINT
import javax.inject.Inject
import javax.inject.Provider

/*
 * A Provider<T> is a factory for objects of type T. If your code depends on multiple objects
 * of type T, inject the Provider<T> and use its get() method to get the instances
 */
class Client @Inject constructor(private val connectionFactory: Provider<Connection>) {
    fun fetchData(): String = connectionFactory.get().doReq()
}

class Connection @Inject constructor() {
    private val endpoint = if (BuildConfig.DEBUG) {
        DEBUG_ENDPOINT
    } else {
        PROD_ENDPOINT
    }

    fun doReq() = endpoint
}