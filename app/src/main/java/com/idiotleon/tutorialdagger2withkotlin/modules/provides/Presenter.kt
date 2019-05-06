package com.idiotleon.tutorialdagger2withkotlin.modules.provides

import com.idiotleon.tutorialdagger2withkotlin.MainActivity
import com.idiotleon.tutorialdagger2withkotlin.essentials.Client
import com.idiotleon.tutorialdagger2withkotlin.modules.provides.persist.DAO
import com.idiotleon.tutorialdagger2withkotlin.modules.provides.persist.Transaction
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Provider

class Presenter @Inject constructor(
    /*
     * A Lazy<T> is a factory for objects of type T.
     * If your code needs a lazily created singleton of type T,
     * inject the Lazy<T> and use its get() method to get the instances
     */
    private val client: Lazy<Client>,
    /*
     * A Provider<T> is a factory for objects of type T. If your code depends on multiple objects
     * of type T, inject the Provider<T> and use its get() method to get the instances
     */
    private val transactionFactory: Provider<Transaction>,
    private val persist: DAO
) {
    fun connect(show: (String?) -> Unit) {
        val data: String = client.get().fetchData()
        show(data)
        persist.store(transactionFactory.get(), data)
    }
}

@Module
object DAOModule {
    @JvmStatic
    @Provides
    fun dao() = DAO.get()

    @JvmStatic
    @Provides
    fun transaction() = Transaction(System.currentTimeMillis())
}

@Component(modules = [DAOModule::class])
interface PresenterFactory {
    fun inject(activity: MainActivity): MainActivity
    fun presenter(): Presenter
}