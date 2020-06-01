package de.abauer.giphy_clean_architecture.domain.repository

import de.abauer.giphy_clean_architecture.domain.model.DataResult
import de.abauer.giphy_clean_architecture.domain.model.Giphy

interface TrendingGiphysRepository {
    suspend fun getTrending(): DataResult<List<Giphy>>
}