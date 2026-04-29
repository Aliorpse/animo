package tech.aliorpse.animo.bangumi.model

import kotlinx.serialization.Serializable

@Serializable
data class BangumiTrendingResponse(
    val data: List<Data>,
    val total: Int
) {
    @Serializable
    data class Data(
        val subject: Subject,
        val count: Int
    ) {
        @Serializable
        data class Subject(
            val id: Int,
            val name: String,
            val nameCN: String,
            val type: Int,
            val info: String,
            val rating: Rating,
            val locked: Boolean,
            val nsfw: Boolean,
            val images: Images
        ) {
            @Serializable
            data class Rating(
                val rank: Int,
                val count: List<Int>,
                val score: Double,
                val total: Int
            )

            @Serializable
            data class Images(
                val large: String,
                val common: String,
                val medium: String,
                val small: String,
                val grid: String
            )
        }
    }
}
