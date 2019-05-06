package com.idiotleon.tutorialdagger2withkotlin.components

import com.idiotleon.tutorialdagger2withkotlin.MainActivity
import dagger.BindsInstance
import dagger.Lazy
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Named

class Presenter @Inject constructor(private val client: Lazy<Client>) {
    fun connect(show: (String?) -> Unit) {
        val data: String = client.get().fetchData()
        show(data)
    }
}

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