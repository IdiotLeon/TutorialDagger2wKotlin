package com.idiotleon.tutorialdagger2withkotlin.components

import com.idiotleon.tutorialdagger2withkotlin.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Named

class Presenter @Inject constructor(private val client: Lazy<Client>) {
    fun connect(show: (String?) -> Unit) {
        val data: String = client.get().fetchData()
        show(data)
    }
}

@Component(dependencies = [NetworkFactory::class])
interface PresenterFactory {
    fun presenter(): Presenter
    fun inject(act: MainActivity)
}