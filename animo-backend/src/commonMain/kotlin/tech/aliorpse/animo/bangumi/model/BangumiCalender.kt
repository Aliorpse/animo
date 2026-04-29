package tech.aliorpse.animo.bangumi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BangumiCalender(
    val weekday: Weekday,
    val items: List<Item>
) {
    @Serializable
    data class Weekday(
        val en: String,
        val cn: String,
        val ja: String,
        val id: Int
    )

    @Serializable
    data class Item(
        val id: Int,
        val url: String,
        val type: Int,
        @SerialName("nameCN") val name: String,
        @SerialName("name") val nameJa: String,
        val summary: String,
        @SerialName("air_date") val airDate: String,
        @SerialName("air_weekday") val airWeekday: Int,
        val rating: Rating? = null,
        val rank: Int? = null,
        val images: Images? = null,
        val collection: Collection? = null
    )

    @Serializable
    data class Rating(
        val total: Int,
        val count: Map<String, Int>,
        val score: Double
    )

    @Serializable
    data class Images(
        val large: String,
        val common: String,
        val medium: String,
        val small: String,
        val grid: String
    )

    @Serializable
    data class Collection(
        val doing: Int
    )
}
