package com.idiotleon.tutorialdagger2withkotlin.basic

import com.idiotleon.tutorialdagger2withkotlin.BuildConfig
import com.idiotleon.tutorialdagger2withkotlin.extra.*

class NaiveNetworkClient {
    private val connection = if (BuildConfig.DEBUG) {
        ConnectionImpl(DEBUG_ENDPOINT)
    } else {
        ConnectionImpl(PROD_ENDPOINT)
    }

    fun fetchData(): String {
        var data = connection.doReq()

        return data
    }
}

class FactoryNetworkClient {
    private val connection = ConnectionFactory.getConnection()
    fun fetchData(): String {
        var data = connection.doReq()

        return data
    }
}

class DINetworkClient(private val connection: NetworkConnection) {
    fun fetchData(): String {
        var data = connection.doReq()

        return data
    }
}