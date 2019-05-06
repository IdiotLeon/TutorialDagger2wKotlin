package com.idiotleon.tutorialdagger2withkotlin.components

import com.idiotleon.tutorialdagger2withkotlin.BuildConfig
import com.idiotleon.tutorialdagger2withkotlin.extra.DEBUG_ENDPOINT
import com.idiotleon.tutorialdagger2withkotlin.extra.PROD_ENDPOINT
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object EndpointModule {
    @JvmStatic
    @Provides
    @Named("endpoint")
    fun providesEndpoint() = if (BuildConfig.DEBUG) {
        DEBUG_ENDPOINT
    } else {
        PROD_ENDPOINT
    }
}

@Component(modules = [EndpointModule::class])
interface RootFactory {
    fun presenterFactory(): PresenterFactory
}

object App {
    val rootFactor = DaggerRootFactory.create()
}