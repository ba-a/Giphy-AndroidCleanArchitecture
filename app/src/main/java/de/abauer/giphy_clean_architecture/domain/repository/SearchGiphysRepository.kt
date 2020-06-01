package de.abauer.giphy_clean_architecture.domain.repository

import de.abauer.giphy_clean_architecture.domain.model.DataResult
import de.abauer.giphy_clean_architecture.domain.model.Giphy

interface SearchGiphysRepository {
    suspend fun searchForGiphys(searchInput: String): DataResult<List<Giphy>>
}