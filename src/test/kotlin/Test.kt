import com.calmwolfs.ktvalorantapi.KtValorantApi
import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.ktvalorantapi.utils.GsonUtils

fun main() {
    val api = KtValorantApi(GsonUtils.getApiKey())

//    val testData = api.getMatches(Region.ASIA_PACIFIC, Platform.PC, PlayerName("dloraH#0001"))
    val testData = api.getMatchById(Region.ASIA_PACIFIC, "f5d43540-51a2-41e0-8d02-c3bb8cc11a8b")
    println(testData)
    api.closeClient()
}