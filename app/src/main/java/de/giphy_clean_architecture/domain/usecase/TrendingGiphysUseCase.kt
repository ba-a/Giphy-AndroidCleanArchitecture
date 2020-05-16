package de.giphy_clean_architecture.domain.usecase

import de.giphy_clean_architecture.data.helper.ControlledRunner
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.repository.GiphyTrendingRepository

class TrendingGiphysUseCase(
    private val giphyTrendingRepository: GiphyTrendingRepository,
    private val controlledRunner: ControlledRunner<DataResult<List<Giphy>>>
) {
    suspend operator fun invoke(): DataResult<List<Giphy>> =
        controlledRunner.joinPreviousOrRun {
            giphyTrendingRepository.getTrending()
        }
}