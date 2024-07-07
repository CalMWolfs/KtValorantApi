package dynamictypes

abstract class DynamicType<T, E : Enum<E>>(
    open val name: String
) {
    val registryName get() = name.uppercase()

    companion object {
        fun <T : DynamicType<T, E>, E : Enum<E>> getFromRegistry(name: String, registry: Map<String, T>): T? {
            return registry[name]
        }

        fun <T : DynamicType<T, E>, E : Enum<E>> getFromRegistry(value: E, registry: Map<String, T>): T? {
            return registry[value.name.uppercase()]
        }

        fun <T> addToRegistry(name: String, value: T, registry: MutableMap<String, T>) {
            if (!registry.containsKey(name)) {
                registry[name] = value
            }
        }
    }
}
