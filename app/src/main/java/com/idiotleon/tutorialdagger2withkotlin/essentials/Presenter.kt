package com.idiotleon.tutorialdagger2withkotlin.essentials

import com.idiotleon.tutorialdagger2withkotlin.MainActivity
import dagger.Component
import javax.inject.Inject

class Presenter @Inject constructor(
    private val client: Lazy<Client>,
    private val persist: DAO
) {

    fun connect(show: (String?) -> Unit) {
        val data = client.get().fetchData()
        show(data)
        persist.store(data)
    }
}

@Component
interface PresenterFactory {
    fun inject(activity: MainActivity): MainActivity
    fun presenter(): Presenter
}