package de.giphy_clean_architecture.domain.usecase

import de.giphy_clean_architecture.domain.helper.ControlledRunner
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.repository.TrendingGiphysRepository

class TrendingGiphysUseCase(
    private val trendingGiphysRepository: TrendingGiphysRepository,
    private val controlledRunner: ControlledRunner<DataResult<List<Giphy>>>
) {
    suspend fun getTrendingGiphys(): DataResult<List<Giphy>> =
        controlledRunner.joinPreviousOrRun {
            trendingGiphysRepository.getTrending()
        }
}