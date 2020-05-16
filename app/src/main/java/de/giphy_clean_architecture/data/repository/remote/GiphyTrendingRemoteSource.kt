package de.giphy_clean_architecture.data.repository.remote

import de.giphy_clean_architecture.data.model.GiphyTrends
import de.giphy_clean_architecture.data.service.ApiService
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.ErrorModel
import de.giphy_clean_architecture.domain.repository.GiphyTrendingRepository
import timber.log.Timber
import java.lang.Exception

class GiphyTrendingRemoteSource(
    private val apiKey: String,
    private val apiService: ApiService
) : GiphyTrendingRepository {

    override suspend fun getTrending(): DataResult<GiphyTrends> {
        return try {
            DataResult.Success(apiService.getTrendingGiphsys(apiKey))
        } catch (e: Exception) {
            // TODO
            Timber.e(e, "Error getting giphy trends")
            DataResult.Error(ErrorModel("Some error", 404))
        }
    }
}