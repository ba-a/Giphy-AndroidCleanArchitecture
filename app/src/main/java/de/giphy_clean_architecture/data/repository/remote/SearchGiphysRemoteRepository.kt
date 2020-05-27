package de.giphy_clean_architecture.data.repository.remote

import de.giphy_clean_architecture.AppDispatchers
import de.giphy_clean_architecture.data.repository.remote.mapper.TrendingGiphyRemoteMapper
import de.giphy_clean_architecture.data.service.ApiErrorHandler
import de.giphy_clean_architecture.data.service.ApiService
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.repository.SearchGiphyRepository
import kotlinx.coroutines.withContext

class SearchGiphysRemoteRepository(
    private val apiKey: String,
    private val apiService: ApiService,
    private val trendingGiphyRemoteMapper: TrendingGiphyRemoteMapper,
    private val appDispatchers: AppDispatchers,
    private val apiErrorHandler: ApiErrorHandler
) : SearchGiphyRepository {

    override suspend fun searchForGiphys(searchInput: String): DataResult<List<Giphy>> =
        withContext(appDispatchers.io) {
            try {
                val searchGyphisRemote = apiService.searchForGiphys(apiKey, searchInput)
                DataResult.Success(trendingGiphyRemoteMapper.invoke(searchGyphisRemote))
            } catch (e: Exception) {
                DataResult.Error(apiErrorHandler.traceErrorException(e))
            }
        }
}