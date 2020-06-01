package de.giphy_clean_architecture.data.repository.remote

import de.giphy_clean_architecture.AppDispatchers
import de.giphy_clean_architecture.data.repository.remote.mapper.GiphyRemoteMapper
import de.giphy_clean_architecture.data.service.ApiErrorHandler
import de.giphy_clean_architecture.data.service.ApiService
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.repository.SearchGiphysRepository
import kotlinx.coroutines.withContext

class SearchGiphysRemoteRepository(
    private val apiKey: String,
    private val apiService: ApiService,
    private val giphyRemoteMapper: GiphyRemoteMapper,
    private val appDispatchers: AppDispatchers,
    private val apiErrorHandler: ApiErrorHandler
) : SearchGiphysRepository {

    override suspend fun searchForGiphys(searchInput: String): DataResult<List<Giphy>> =
        withContext(appDispatchers.io) {
            try {
                val searchGyphisRemote = apiService.searchForGiphys(apiKey, searchInput)
                DataResult.Success(giphyRemoteMapper.invoke(searchGyphisRemote))
            } catch (e: Exception) {
                DataResult.Error(apiErrorHandler.traceErrorException(e))
            }
        }
}