package de.abauer.giphy_clean_architecture.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val bitly_gif_url: String?,
    val bitly_url: String?,
    val content_url: String?,
    val embed_url: String?,
    val id: String?,
    val images: Images?,
    val import_datetime: String?,
    val is_sticker: Int,
    val rating: String?,
    val slug: String?,
    val source: String?,
    val source_post_url: String?,
    val source_tld: String?,
    val title: String?,
    val trending_datetime: String?,
    val type: String?,
    val url: String?
)