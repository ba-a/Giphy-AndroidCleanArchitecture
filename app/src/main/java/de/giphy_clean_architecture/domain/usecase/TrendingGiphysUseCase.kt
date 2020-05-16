package de.giphy_clean_architecture.domain.usecase

import de.giphy_clean_architecture.data.model.GiphyTrends
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.repository.GiphyTrendingRepository

class TrendingGiphysUseCase(
    private val giphyTrendingRepository: GiphyTrendingRepository
) {
    suspend operator fun invoke(): DataResult<GiphyTrends> =
        giphyTrendingRepository.getTrending()
}