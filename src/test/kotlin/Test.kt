import com.calmwolfs.ktvalorantapi.KtValorantApi
import com.calmwolfs.ktvalorantapi.enums.Region
import com.calmwolfs.ktvalorantapi.utils.GsonUtils

fun main() {
    val api = KtValorantApi(GsonUtils.getApiKey())

    val testData = api.getQueueStatus(Region.ASIA_PACIFIC)
    println(testData)
}