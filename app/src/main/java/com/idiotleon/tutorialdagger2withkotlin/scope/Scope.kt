package com.idiotleon.tutorialdagger2withkotlin.scope

import dagger.Component
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

/*
 * Custom Scope:
 * Create a custom scope with @Qualifier annotated annotation class
 *
 * Dagger treats custom @Scope exactly as if it were a singleton
 *
 * Allow strictly hierarchical scoping in the DAG
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@AppScope
class Environment @Inject constructor() {}

@AppScope
@Component
interface RootFactory {
    fun env(): Environment
}

class Presenter @Inject constructor() {}

@Singleton
@Component(dependencies = [RootFactory::class])
interface PresenterFactory {
    fun presenter(): Presenter
}

object App {
    fun test() {}
}

/*
 * Dagger cannot prevent one from holding the references to multiple components with the same scope, even if that is not your intent
 *
 * Dagger cannot prevent one from holding a reference to a component whose parent is stale
 *
 * Singletons are only singletons in the context of their components;
 * 2 components will produce singletons that are NOT ==
 */