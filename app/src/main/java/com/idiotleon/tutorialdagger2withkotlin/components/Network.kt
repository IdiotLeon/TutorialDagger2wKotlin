package com.idiotleon.tutorialdagger2withkotlin.components

import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

class Client @Inject constructor(private val connectionFactory: Provider<Connection>) {
    fun fetchData(): String = connectionFactory.get().doReq()
}

class Connection @Inject constructor(val endpoint: String) {
    // to return the endpoint url
    fun doReq() = endpoint
}