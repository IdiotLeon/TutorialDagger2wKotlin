package com.idiotleon.tutorialdagger2withkotlin.modules.optional

import android.util.Log
import com.idiotleon.tutorialdagger2withkotlin.extra.Prefix
import dagger.BindsOptionalOf
import dagger.Component
import dagger.Module
import dagger.Provides
import java.util.Optional
import javax.inject.Inject

private const val TAG = "optional"

//region Prefix
@Module
interface OptionalPrefixModule {
    @BindsOptionalOf
    fun optionalPrefix(): Prefix
}
//endregion

//region Logger
class Logger @Inject constructor(private val prefix: Optional<Prefix>) {
    fun log() {
        val msg = if (!prefix.isPresent) {
            "default"
        } else {
            prefix.get().prefix()
        }
        Log.d(TAG, "message: ${msg}")
    }
}
//endregion

//region Prefixless
@Module(includes = [OptionalPrefixModule::class])
object DefaultPrefixModule

@Component(modules = [DefaultPrefixModule::class])
interface DefaultPrefixFactory {
    fun data(): Logger
}
//endregion

//region Custom Prefix
class CustomPrefix(private val prefix: String) : Prefix {
    override fun prefix() = prefix
}

@Module
interface PrefixImplModule {
    fun providesPrefix(prefix: CustomPrefix): Prefix
}

@Module(includes = [OptionalPrefixModule::class, PrefixImplModule::class])
object CustomPrefixModule {
    @JvmStatic
    @Provides
    fun providesPrefixImpl() = CustomPrefix("oootori sama")
}

@Component(modules = [CustomPrefixModule::class])
interface CustomPrefixFactory {
    fun data(): Logger
}
//endregion

//region Test Driver
object Optional {
    fun test() {
        DaggerDefaultPrefixFactory.create().data().log()
        DaggerCustomPrefixFactory.create().data().log()
    }
}
//endregion