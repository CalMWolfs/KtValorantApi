import com.calmwolfs.ktvalorantapi.KtValorantApi
import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.ktvalorantapi.utils.GsonUtils

fun main() {
    val api = KtValorantApi(GsonUtils.getApiKey())

//    val testData = api.getMatches(Region.ASIA_PACIFIC, Platform.PC, PlayerName("dloraH#0001"))\
    val testData = api.getQueueStatus(Region.ASIA_PACIFIC)
    println(testData)
    api.closeClient()
}