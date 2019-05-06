package com.idiotleon.tutorialdagger2withkotlin.components

import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject
import javax.inject.Provider

class Client @Inject constructor(private val connectionFactory: Provider<Connection>) {
    fun fetchData(): String = connectionFactory.get().doReq()
}

class Connection @Inject constructor(val endpoint: String) {
    // to return the endpoint url
    fun doReq() = endpoint
}

@Component
interface NetworkFactory {
    @Component.Builder
    interface Builder {
        fun build(): NetworkFactory

        @BindsInstance
        fun endpoint(id: String): Builder
    }

    /*
     * The parent component must explicitly supply the object that the dependent component needs
     */
    fun client(): Client
}