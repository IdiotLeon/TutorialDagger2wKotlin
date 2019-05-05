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
    private val client: Lazy<Client>,
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