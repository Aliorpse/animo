package tech.aliorpse.animo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AniCalendarResponse(
    @SerialName("list")
    val data: List<Season>
) {
    @Serializable
    data class Season(
        val seasonId: SeasonInfo,
        val list: List<Item>
    )

    @Serializable
    data class SeasonInfo(
        val year: Int,
        val season: String,
        val id: String
    )

    @Serializable
    data class Item(
        val bangumiId: Int,
        val name: String,
        val aliases: List<String> = emptyList(),
        val begin: String,
        val end: String? = null,
        val recurrence: Recurrence? = null
    )

    @Serializable
    data class Recurrence(
        val startTime: String,
        val intervalMillis: Long
    )
}
