package de.abauer.giphy_clean_architecture.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images(
    val fixed_height: FixedHeight?
)