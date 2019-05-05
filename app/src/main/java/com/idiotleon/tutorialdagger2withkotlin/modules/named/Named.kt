package com.idiotleon.tutorialdagger2withkotlin.modules.named

import android.util.Log
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

private const val TAG = "NAMED"

class NamedObject constructor(val name: String)

@Module
object NamedModule {
    @JvmStatic
    @Provides
    @Named("fname")
    fun provideFirstName() = "tom"

    @JvmStatic
    @Provides
    @Named("lname")
    fun provideLastName() = "swift"

    @JvmStatic
    @Provides
    fun provideNamedObject(@Named("fname") fname: String, @Named("lname") lname: String) =
        NamedObject("${fname} ${lname}")
}

@Component(modules = [NamedModule::class])
interface NamedFactory {
    @Named("fname")
    fun firstName(): String

    @Named("lname")
    fun lastName(): String

    fun nameObject(): NamedObject
}

object Named {
    fun test() {
        val factory = DaggerNamedFactory.create()
        Log.d(TAG, "${factory.firstName()}")
        Log.d(TAG, "${factory.lastName()}")
        Log.d(TAG, "${factory.nameObject()}")
    }
}