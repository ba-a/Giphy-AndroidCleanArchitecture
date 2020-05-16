package de.giphy_clean_architecture.data.model

data class Pagination(
    val count: Int,
    val offset: Int,
    val total_count: Int
)