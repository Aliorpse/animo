package tech.aliorpse.animo

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import tech.aliorpse.animo.model.AniCalendarResponse
import tech.aliorpse.animo.model.BangumiTrendingResponse

object AnimoService {
    private val httpClient = HttpClient {
        defaultRequest {
            header(HttpHeaders.UserAgent, "aliorpse/animo")
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun getTrending() =
        httpClient.get("https://next.bgm.tv/p1/trending/subjects?type=2").body<BangumiTrendingResponse>()

    suspend fun getCalendar() =
        httpClient.get("https://api.animeko.org/v1/schedule/seasons/latest").body<AniCalendarResponse>()

    fun getImageUrlFromBangumiId(bangumiId: Int) =
        "https://api.bgm.tv/v0/subjects/$bangumiId/image?type=large"
}
