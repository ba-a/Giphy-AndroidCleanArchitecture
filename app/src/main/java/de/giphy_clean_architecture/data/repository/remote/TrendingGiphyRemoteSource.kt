package de.giphy_clean_architecture.data.repository.remote

import de.giphy_clean_architecture.AppDispatchers
import de.giphy_clean_architecture.data.repository.remote.mapper.TrendingGiphyRemoteMapper
import de.giphy_clean_architecture.data.service.ApiErrorHandle
import de.giphy_clean_architecture.data.service.ApiService
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.repository.TrendingGiphyRepository
import kotlinx.coroutines.withContext
import java.lang.Exception

class TrendingGiphyRemoteSource(
    private val apiKey: String,
    private val apiService: ApiService,
    private val trendingGiphyRemoteMapper: TrendingGiphyRemoteMapper,
    private val appDispatchers: AppDispatchers,
    private val apiErrorHandle: ApiErrorHandle
) : TrendingGiphyRepository {

    override suspend fun getTrending(): DataResult<List<Giphy>> =
        withContext(appDispatchers.io) {
            try {
                val trendingGiphysRemote = apiService.getTrendingGiphsys(apiKey)
                DataResult.Success(trendingGiphyRemoteMapper.invoke(trendingGiphysRemote))
            } catch (e: Exception) {
                DataResult.Error(apiErrorHandle.traceErrorException(e))
            }
        }
}