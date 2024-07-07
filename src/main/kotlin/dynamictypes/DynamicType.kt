package dynamictypes

abstract class DynamicType<T>(
    open val name: String
) {
    val registryName get() = name.uppercase()

    companion object {
        protected val registry: MutableMap<Class<*>, Map<String, Any>> = mutableMapOf()

        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        protected inline fun <reified T> getRegistry(): Map<String, T> {
            return registry.getOrPut(T::class.java) { mutableMapOf() } as Map<String, T>
        }

        @JvmStatic
        protected inline fun <reified T> getFromRegistry(name: String): T? {
            return getRegistry<T>()[name]
        }

        @JvmStatic
        protected inline fun <reified T : DynamicType<T>> addToRegistry(value: DynamicType<T>) {
            val registry = getRegistry<T>() as MutableMap<String, T>
            val name = value.registryName
            if (!registry.containsKey(name)) {
                registry[name] = value as T
            }
        }
    }
}
