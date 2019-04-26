package com.idiotleon.tutorialdagger2withkotlin.modules.multibind

import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.*
import javax.inject.Inject
import kotlin.reflect.KClass

private const val TAG = "MULTIBIND"

data class Decorator(val displayName: String, val fn: (String) -> String)

class SetProcessor @Inject constructor(private val decorators: Set<Decorator>) {
    fun decorate(str: String) = decorators.map { it -> it.fn(str) }
}

class StringMapProcessor @Inject constructor(private val decorators: Map<String, Decorator>) {
    fun decorate(str: String) = decorators.entries.map { it -> "${it.key}->${it.value.fn(str)}" }
    fun decorate(key: String, str: String) = decorators[key]?.fn?.invoke(str)
}

class ClassMapProcessor @Inject constructor(private val decorators: Map<Class<*>, Decorator>) {
    fun decorate(str: String) = decorators.entries.map { it -> "${it.key} -> ${it.value.fn(str)}" }
    fun decorate(key: Class<*>, str: String) = decorators[key]?.fn?.invoke(str)
}

class EnumMapProcessor @Inject constructor(private val decorators: Map<DecoratorType, Decorator>) {
    fun decorate(str: String) = decorators.entries.map { it -> "${it.key} -> ${it.value.fn(str)}" }
    fun decorate(key: DecoratorType, str: String) = decorators[key]?.fn?.invoke(str)
}

class ComplexMapProcessor @Inject constructor(private val decorators: Map<ComplexMapKey, Decorator>) {
    fun decorate(str: String) = decorators.entries.map { it -> "${it.key}->${it.value.fn(str)}" }
    fun decorate(key: ComplexMapKey, str: String) = decorators[key]?.fn?.invoke(str)
}

/**
 * Enum Map
 */
enum class DecoratorType { SPARKLE, EMPHATIC, YELL }

/**
 * Set
 */
@Module
object SetModule {
    @JvmStatic
    @Provides
    @IntoSet
    fun provideOneDecorator() = Decorator("Sparkle", { s -> "*$s*" })

    @JvmStatic
    @Provides
    @ElementsIntoSet
    fun provideSomeDecorators() = setOf<Decorator>(
        Decorator("Emphatic", { s -> "$s!!" }),
        Decorator("Yell", { s -> ":-0 $s" })
    )
}

/**
 * String Map
 */
@Module
object StringMapModule {
    @JvmStatic
    @Provides
    @IntoMap
    @StringKey("sparkle")
    fun provideDecorator() = Decorator("Sparkle", { s -> "*$s*" })

    @JvmStatic
    @Provides
    @IntoMap
    @StringKey("emphatic")
    fun provideDecorator2() = Decorator("Emphatic", { s -> "$s!!" })
}

/**
 * Class Map
 */
@Module
object ClassMapModule {
    @JvmStatic
    @Provides
    @IntoMap
    @ClassKey(Int::class)
    fun provideDecorator1() = Decorator("Sparkle", { s -> "*$s" })

    @JvmStatic
    @Provides
    @IntoMap
    @ClassKey(Long::class)
    fun provideDecorator2() = Decorator("Emphatic", { s -> "$s!!" })
}

@MapKey
annotation class MapKey0(val value: DecoratorType)

/**
 * Enum Map
 */
@Module
object EnumMapModule {
    @JvmStatic
    @Provides
    @IntoMap
    @MapKey0(DecoratorType.SPARKLE)
    fun provideDecorator1() = Decorator("Sparkle", { s -> "*$s*" })

    @JvmStatic
    @Provides
    @IntoMap
    @MapKey0(DecoratorType.EMPHATIC)
    fun provideDecorator2() = Decorator("Emphatic", { s -> "$s!!" })

    @JvmStatic
    @Provides
    @IntoMap
    @MapKey0(DecoratorType.YELL)
    fun provideDecorator3() = Decorator("Yell", { s -> ":-0 $s" })
}

/**
 * Multi-member Key Map
 */
@MapKey(unwrapValue = false)
annotation class ComplexMapKey(
    val klass: KClass<*> = Object::class,
    val name: String,
    val type: DecoratorType,
    val id: Int,
    val weight: Long
)

@Module
object ComplexMapModule {
    @JvmStatic
    @Provides
    @IntoMap
    @ComplexMapKey(name = "sparkle", type = DecoratorType.SPARKLE, id = 0, weight = 100L)
    fun provideDecorator1() = Decorator("SPARKLE", { s -> "*$s" })

    @JvmStatic
    @Provides
    @IntoMap
    @ComplexMapKey(name = "emphatic", type = DecoratorType.EMPHATIC, id = 1, weight = 150L)
    fun provideDecorator2() = Decorator("Emphatic", { s -> "$s!!" })

    @JvmStatic
    @Provides
    @IntoMap
    @ComplexMapKey(name = "yell", type = DecoratorType.YELL, id = 2, weight = 200L)
    fun provideDecorator3() = Decorator("Yell", { s -> ":-0 $s" })
}

@Component(modules = [SetModule::class, StringMapModule::class, ClassMapModule::class, EnumMapModule::class, ComplexMapModule::class])
interface MultibindingFactory {
    fun setProcessor(): SetProcessor
    fun stringProcessor(): StringMapProcessor
    fun classProcessor(): ClassMapProcessor
    fun enumProcessor(): EnumMapProcessor
    fun complexProcessor(): ComplexMapProcessor
}

object Multibinding {
    fun test(str: String): String {
        val factory = DaggerMultibindingFactory.create()

        val out = mutableListOf<String>()

        out.addAll(factory.setProcessor().decorate(str))
        out.addAll(factory.stringProcessor().decorate(str))
        out.addAll(factory.classProcessor().decorate(str))
        out.addAll(factory.enumProcessor().decorate(str))
        out.addAll(factory.complexProcessor().decorate(str))

        return out.joinToString("\n")
    }
}