package tech.aliorpse.animo.bangumi

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import tech.aliorpse.animo.bangumi.model.BangumiCalender
import tech.aliorpse.animo.bangumi.model.BangumiTrendingResponse

object BangumiService {
    private val httpClient = HttpClient {
        defaultRequest {
            header(HttpHeaders.UserAgent, "aliorpse/animo")
        }

        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getTrending() =
        httpClient.get("https://next.bgm.tv/p1/trending/subjects?type=2").body<BangumiTrendingResponse>()

    suspend fun getCalendar() =
        httpClient.get("https://api.bgm.tv/calendar").body<List<BangumiCalender>>()
}
