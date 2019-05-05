package com.idiotleon.tutorialdagger2withkotlin.essentials

import com.idiotleon.tutorialdagger2withkotlin.MainActivity
import dagger.Component
import javax.inject.Inject

class Presenter {
    @Inject
    constructor()

    // An example of method injection.
    // Method injection is seldom useful.
    // Use only as a last resort.
    var client: Client? = null
        @Inject set

    fun connect(show: (String?) -> Unit) {
        val data = client?.fetchData()
        show(data)
    }
}

@Component
interface PresenterFactory {
    fun inject(activity: MainActivity): MainActivity
    fun presenter(): Presenter
}