package de.giphy_clean_architecture.domain.usecase

import de.giphy_clean_architecture.domain.helper.ControlledRunner
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.repository.SearchGiphyRepository

class SearchGiphysUseCase(
    private val searchGiphysRepository: SearchGiphyRepository,
    private val controlledRunner: ControlledRunner<DataResult<List<Giphy>>>
) {
    suspend fun searchGiphysForText(searchInput: String): DataResult<List<Giphy>> =
        controlledRunner.cancelPreviousThenRun {
            searchGiphysRepository.searchForGiphys(searchInput)
        }
}