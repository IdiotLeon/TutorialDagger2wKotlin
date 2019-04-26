package com.idiotleon.tutorialdagger2withkotlin.essentials

import com.idiotleon.tutorialdagger2withkotlin.BuildConfig
import com.idiotleon.tutorialdagger2withkotlin.extra.DEBUG_ENDPOINT
import com.idiotleon.tutorialdagger2withkotlin.extra.PROD_ENDPOINT
import javax.inject.Inject
import javax.inject.Provider

class Client(private val connectionFactory: Provider<Connection>) {
    fun fetchData(): String = connectionFactory.get().doReq()
}

class Connection {
    private val endpoint = if (BuildConfig.DEBUG) {
        DEBUG_ENDPOINT
    } else {
        PROD_ENDPOINT
    }

    fun doReq() = endpoint
}