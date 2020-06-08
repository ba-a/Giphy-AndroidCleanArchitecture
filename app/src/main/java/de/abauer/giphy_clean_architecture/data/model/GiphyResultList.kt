package de.abauer.giphy_clean_architecture.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyResultList(
    val `data`: List<Data>?
)