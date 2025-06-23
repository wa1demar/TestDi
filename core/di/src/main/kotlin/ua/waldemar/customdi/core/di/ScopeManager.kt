package ua.waldemar.customdi.core.di

typealias Module = Scope.Builder.() -> Unit

typealias Qualifier = String

private data class Key(val type: Class<*>, val qualifier: Qualifier? = null)

object ScopeManager {
    private val scopes = mutableMapOf<String, Scope>()

    @Synchronized
    fun createScope(
        key: String,
        module: Module
    ): Scope =
        scopes.getOrPut(key) { Scope.Builder().apply(module).build() }

    @Synchronized
    fun getScope(key: String): Scope =
        scopes[key] ?: error("Scope '$key' not found")

    @Synchronized
    fun destroyScope(key: String) =
        scopes.remove(key)?.clear()
}

class Scope private constructor(
    private val singles: MutableMap<Key, Any>,
    private val providers: Map<Key, Scope.() -> Any>,
    private val factories: Map<Key, Scope.() -> Any>
) {

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(clazz: Class<T>, qualifier: Qualifier? = null): T {
        val key = Key(clazz, qualifier)
        return when {
            singles.containsKey(key) -> singles[key] as T
            providers.containsKey(key) ->
                providers[key]!!.invoke(this).also { singles[key] = it } as T
            factories.containsKey(key) ->
                factories[key]!!.invoke(this) as T
            else -> if (qualifier != null) {
                error("No provider for ${clazz.simpleName} with qualifier=$qualifier")
            } else {
                error("No provider for ${clazz.simpleName}")
            }
        }
    }

    inline fun <reified T : Any> get(): T = get(T::class.java)

    inline fun <reified T : Any> get(qualifier: Qualifier? = null): T = get(T::class.java, qualifier)

    fun clear() {
        singles.clear()
    }

    class Builder {
        private val providers = mutableMapOf<Key, Scope.() -> Any>()
        private val factories = mutableMapOf<Key, Scope.() -> Any>()
        private val singlesCache = mutableMapOf<Key, Any>()

        @PublishedApi
        internal fun <T : Any> _single(
            clazz: Class<T>,
            qualifier: Qualifier? = null,
            provider: Scope.() -> T
        ) {
            providers[Key(clazz, qualifier)] = provider
        }

        inline fun <reified T : Any> single(noinline provider: Scope.() -> T) =
            _single(T::class.java, null, provider)

        inline fun <reified T : Any> single(qualifier: Qualifier, noinline provider: Scope.() -> T) =
            _single(T::class.java, qualifier, provider)

        @PublishedApi
        internal fun <T : Any> _factory(
            clazz: Class<T>,
            qualifier: Qualifier? = null,
            provider: Scope.() -> T) {
            factories[Key(clazz, qualifier)] = provider
        }

        inline fun <reified T : Any> factory(noinline provider: Scope.() -> T) =
            _factory(T::class.java, null, provider)

        inline fun <reified T : Any> factory(qualifier: Qualifier, noinline provider: Scope.() -> T) =
            _factory(T::class.java, qualifier, provider)

        fun build(): Scope =
            Scope(
                providers = providers.toMap(),
                factories = factories.toMap(),
                singles = singlesCache
            )
    }
}