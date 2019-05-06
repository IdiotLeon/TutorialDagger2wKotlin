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

/**
 * Field injection is more useful (than method injection),
 * especially when the application code cannot create the object,
 * into which it must inject dependencies,
 * such as (Android)Activities.
 * 3 steps for field injection:
 * 1. to add an injection method to the abstract factory
 * 2. to annotate the field to be injected
 * 3. to use the injection method on dagger's implementation of the abstract factory to inject the dependencies
 */
@Component
interface PresenterFactory {
    // field injection, step 1: to add a new method to the abstract factory
    fun inject(activity: MainActivity): MainActivity

    fun presenter(): Presenter
}