package de.giphy_clean_architecture.domain.repository

import de.giphy_clean_architecture.data.model.GiphyTrends
import de.giphy_clean_architecture.domain.model.DataResult

interface GiphyTrendingRepository {
    suspend fun getTrending(): DataResult<GiphyTrends>
}