import datatypes.PlayerName
import datatypes.SearchSize
import enums.Platform
import enums.Region

fun main(args: Array<String>) {

    val valorantApi = KtValorantApi("HDEV-1fca0c7c-34e0-4269-9bec-8e879deb9cb8")
//    val testResult = valorantApi.getMatches(Region.ASIA_PACIFIC, Platform.PC, PlayerName("dloraH#0001"), SearchSize(1))
    val testResult = valorantApi.getStoredMatches(Region.ASIA_PACIFIC, PlayerName("dloraH#0001"), SearchSize(1))
//    val testResult = valorantApi.getQueueStatus(Region.ASIA_PACIFIC)
    println(testResult)
}
