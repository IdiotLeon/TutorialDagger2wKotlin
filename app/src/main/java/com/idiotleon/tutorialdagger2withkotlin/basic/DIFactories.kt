package com.idiotleon.tutorialdagger2withkotlin.basic

import com.idiotleon.tutorialdagger2withkotlin.BuildConfig
import com.idiotleon.tutorialdagger2withkotlin.extra.ConnectionImpl
import com.idiotleon.tutorialdagger2withkotlin.extra.DEBUG_ENDPOINT
import com.idiotleon.tutorialdagger2withkotlin.extra.PROD_ENDPOINT

class MainPresenterFactory {
    private val clientFactory = NetworkClientFactory()
    fun get() = MainPresenter(clientFactory.get())
}

class NetworkClientFactory {
    private val connectionFactory = NetworkConnectionFactory()
    fun get() = DINetworkClient(connectionFactory.get())
}

class NetworkConnectionFactory {
    private val endpointFactory = EndpointFactory()
    fun get() = ConnectionImpl(endpointFactory.get())
}

class EndpointFactory {
    fun get() = if (BuildConfig.DEBUG) {
        DEBUG_ENDPOINT
    } else {
        PROD_ENDPOINT
    }
}