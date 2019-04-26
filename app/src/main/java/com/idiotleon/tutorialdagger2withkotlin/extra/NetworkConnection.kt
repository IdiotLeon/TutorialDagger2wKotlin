package com.idiotleon.tutorialdagger2withkotlin.extra

import com.idiotleon.tutorialdagger2withkotlin.BuildConfig

const val DEBUG_ENDPOINT = "http://debug.callmeike.net/daggerwithkotlin"
const val PROD_ENDPOINT = "http://prod.callmeike.net/daggerwithkotlin"

interface NetworkConnection {
    fun doReq(): String
}

class ConnectionImpl(private val endpoint: String) : NetworkConnection {
    override fun doReq() = endpoint
}

class ConnectionFactory {
    companion object {
        fun getConnection(): NetworkConnection =
            if (BuildConfig.DEBUG) {
                ConnectionImpl(DEBUG_ENDPOINT)
            } else {
                ConnectionImpl(PROD_ENDPOINT)
            }
    }
}