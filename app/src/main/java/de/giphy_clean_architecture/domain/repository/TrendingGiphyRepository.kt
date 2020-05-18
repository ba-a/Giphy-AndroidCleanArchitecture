package de.giphy_clean_architecture.domain.repository

import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy

interface TrendingGiphyRepository {
    suspend fun getTrending(): DataResult<List<Giphy>>
}