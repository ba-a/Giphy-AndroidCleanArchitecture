package de.giphy_clean_architecture.data.repository.remote

import de.giphy_clean_architecture.AppDispatchers
import de.giphy_clean_architecture.data.model.GiphyTrends
import de.giphy_clean_architecture.data.repository.remote.mapper.GiphyTrendingRemoteMapper
import de.giphy_clean_architecture.data.service.ApiErrorHandle
import de.giphy_clean_architecture.data.service.ApiService
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.ErrorModel
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.repository.GiphyTrendingRepository
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class GiphyTrendingRemoteSource(
    private val apiKey: String,
    private val apiService: ApiService,
    private val mapper: GiphyTrendingRemoteMapper,
    private val appDispatchers: AppDispatchers,
    private val apiErrorHandle: ApiErrorHandle
) : GiphyTrendingRepository {

    override suspend fun getTrending(): DataResult<List<Giphy>> =
        withContext(appDispatchers.io) {
            try {
                DataResult.Success(mapper.invoke(apiService.getTrendingGiphsys(apiKey)))
            } catch (e: Exception) {
                DataResult.Error(apiErrorHandle.traceErrorException(e))
            }
        }
}