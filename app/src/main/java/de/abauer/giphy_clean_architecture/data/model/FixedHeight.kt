package de.abauer.giphy_clean_architecture.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FixedHeight(
    val height: String?,
    val size: String?,
    val url: String?,
    val width: String?
)