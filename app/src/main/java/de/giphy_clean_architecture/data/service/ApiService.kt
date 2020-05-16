package de.giphy_clean_architecture.data.service

import de.giphy_clean_architecture.data.model.GiphyTrends
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/gifs/trending?limit=12&rating=G")
    suspend fun getTrendingGiphsys(@Query("api_key") api_key: String): GiphyTrends

}