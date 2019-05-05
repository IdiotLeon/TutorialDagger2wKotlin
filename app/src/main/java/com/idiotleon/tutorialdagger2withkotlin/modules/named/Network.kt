package com.idiotleon.tutorialdagger2withkotlin.modules.named

import com.idiotleon.tutorialdagger2withkotlin.BuildConfig
import com.idiotleon.tutorialdagger2withkotlin.extra.DEBUG_ENDPOINT
import com.idiotleon.tutorialdagger2withkotlin.extra.PROD_ENDPOINT
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named

class Client @Inject constructor(private val connection: Connection) {
    fun fetchData(): String = connection.doReq()
}

class Connection @Inject constructor(/*@Named("endpoint")*/ val endpoint: String) {
    fun doReq() = endpoint
}

@Component(modules = [ConnectionModule::class])
interface PresenterFactory {
    fun client(): Client
}

@Module
object ConnectionModule {
    @JvmStatic
    @Provides
    // @Named("endpoint")
    fun providesEndpoint() = if (BuildConfig.DEBUG) {
        DEBUG_ENDPOINT
    } else {
        PROD_ENDPOINT
    }
}