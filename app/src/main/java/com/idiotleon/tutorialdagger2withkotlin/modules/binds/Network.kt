package com.idiotleon.tutorialdagger2withkotlin.modules.binds

import com.idiotleon.tutorialdagger2withkotlin.BuildConfig
import com.idiotleon.tutorialdagger2withkotlin.extra.DEBUG_ENDPOINT
import com.idiotleon.tutorialdagger2withkotlin.extra.PROD_ENDPOINT
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Inject

@Component(modules = [ConnectionModule::class])
interface PresenterFactory {
    fun client(): Client
}

@Module
interface ConnectionModule {
    @Binds
    fun bindConnection(connection: NetworkConnection): Connection
}

class Client @Inject constructor(private val connection: Connection) {
    fun fetchData(): String = connection.doReq()
}

interface Connection {
    fun doReq(): String
}

class NetworkConnection @Inject constructor() : Connection {
    protected val endpoint = if (BuildConfig.DEBUG) {
        DEBUG_ENDPOINT
    } else {
        PROD_ENDPOINT
    }

    override fun doReq() = endpoint
}