package dynamictypes

import kotlin.random.Random

data class Weapon private constructor(override val name: String, val damage: Int) : DynamicType<Weapon, WeaponType>(name) {

    companion object {
        private val registry = generateRegistry()

        private fun generateRegistry(): Map<String, Weapon> {
            val registry = mutableMapOf<String, Weapon>()
            getWeapons().forEach {
                val weapon = Weapon(it, Random.nextInt(1, 10))
                addToRegistry(weapon.registryName, weapon, registry)
            }
            return registry
        }

        private fun getWeapons(): List<String> {
            return listOf("Sword", "Axe", "Bow")
        }

        fun getWeapon(weapon: WeaponType): Weapon? {
            return getFromRegistry(weapon, registry)
        }

        fun getWeapon(weapon: String): Weapon? {
            return getFromRegistry(weapon, registry)
        }

        fun getAllWeapons(): List<Weapon> {
            return registry.values.toList()
        }
    }
}