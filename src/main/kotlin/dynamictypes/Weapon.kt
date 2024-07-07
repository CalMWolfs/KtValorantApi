package dynamictypes

data class Weapon private constructor(override val name: String, val damage: Int) : DynamicType<Weapon>(name) {

    companion object {

        fun getWeapon(weapon: String): Weapon? {
            return getFromRegistry<Weapon>(weapon)
        }

        fun getWeapon(weapon: WeaponType): Weapon? {
            return getFromRegistry<Weapon>(weapon.name.uppercase())
        }

        fun getAllWeapons(): List<Weapon> {
            return getRegistry<Weapon>().values.toList()
        }
    }
}