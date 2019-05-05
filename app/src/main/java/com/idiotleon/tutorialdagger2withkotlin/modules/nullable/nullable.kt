package com.idiotleon.tutorialdagger2withkotlin.modules.nullable

import android.util.Log
import com.idiotleon.tutorialdagger2withkotlin.extra.Prefix
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

private const val TAG = "NULL"

class Logger @Inject constructor(private val prefix: Prefix?) {
    fun log() {
        val msg = prefix?.prefix() ?: "default"
        Log.d(TAG, "message: ${msg}")
    }
}

@Module
object DefaultPrefixModule {
    @JvmStatic
    @Provides
    fun providesPrefix(): Prefix? = null
}


@Component(modules = [DefaultPrefixModule::class])
interface DefaultPrefixFactory {
    fun data(): Logger
}

class CustomPrefix(private val prefix: String) : Prefix {
    override fun prefix() = prefix
}

@Module
interface PrefixImplModule {
    @Binds
    fun providesPrefix(prefix: CustomPrefix): Prefix
}

@Module(includes = [PrefixImplModule::class])
object CustomPrefixModule {
    @JvmStatic
    @Provides
    fun providesPrefixImpl() = CustomPrefix("ootori sama")
}

@Component(modules = [CustomPrefixModule::class])
interface CustomPrefixFactory {
    fun data(): Logger
}

object Nullable {
    fun test() {
        DaggerDefaultPrefixFactory.create().data().log()
        DaggerCustomPrefixFactory.create().data().log()
    }
}