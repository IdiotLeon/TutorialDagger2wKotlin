package com.idiotleon.tutorialdagger2withkotlin.components

import com.idiotleon.tutorialdagger2withkotlin.MainActivity
import dagger.Lazy
import dagger.Subcomponent
import javax.inject.Inject

class Presenter @Inject constructor(private val client: Lazy<Client>) {
    fun connect(show: (String?) -> Unit) {
        val data: String = client.get().fetchData()
        show(data)
    }
}

@Subcomponent
interface PresenterFactory {
    fun presenter(): Presenter
    fun inject(act: MainActivity)
}