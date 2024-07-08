import com.calmwolfs.ktvalorantapi.KtValorantApi
import com.calmwolfs.ktvalorantapi.datatypes.PlayerName
import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.ktvalorantapi.utils.GsonUtils

fun main() {
    val api = KtValorantApi(GsonUtils.getApiKey())

    val testData = api.getStoredMmrHistory(Region.ASIA_PACIFIC, PlayerName("dloraH#0001"))
    println(testData)
}