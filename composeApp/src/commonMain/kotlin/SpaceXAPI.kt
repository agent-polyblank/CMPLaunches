import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.king.kmplaunches.model.RocketLaunchExt

class SpaceXAPI(
    private val client: HttpClient,
) {
    suspend fun getAllLaunches(): List<RocketLaunchExt> {
        try {
            val response: HttpResponse = client.get("https://api.spacexdata.com/v4/launches")
            return response.body()
        } catch (e: Exception) {
            throw e
        }
    }
}
