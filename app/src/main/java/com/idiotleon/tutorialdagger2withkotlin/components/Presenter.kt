package com.idiotleon.tutorialdagger2withkotlin.components

import com.idiotleon.tutorialdagger2withkotlin.MainActivity
import dagger.BindsInstance
import dagger.Lazy
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class Presenter @Inject constructor(private val client: Lazy<Client>) {
    fun connect(show: (String?) -> Unit) {
        val data: String = client.get().fetchData()
        show(data)
    }
}

/*
 * In general, a factory needs to be marked with all of scopes used by any of the objects it provides
 */
@Singleton
@Subcomponent
interface PresenterFactory {
    @Subcomponent.Builder
    interface Builder {
        fun build(): PresenterFactory

        @BindsInstance
        fun endpoint(@Named("endpoint") endpoint: String): Builder
    }

    fun presenter(): Presenter
    fun inject(act: MainActivity)
}