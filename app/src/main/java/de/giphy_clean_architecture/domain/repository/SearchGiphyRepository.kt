package de.giphy_clean_architecture.domain.repository

import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy

interface SearchGiphyRepository {
    suspend fun searchForGiphys(searchInput: String): DataResult<List<Giphy>>
}