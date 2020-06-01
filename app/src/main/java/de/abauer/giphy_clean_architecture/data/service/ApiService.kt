package de.abauer.giphy_clean_architecture.data.service

import de.abauer.giphy_clean_architecture.data.model.GiphyResultList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/gifs/trending?limit=25&rating=G")
    suspend fun getTrendingGiphys(@Query("api_key") api_key: String): GiphyResultList

     @GET("v1/gifs/search?limit=25&rating=G&lang=en")
    suspend fun searchForGiphys(@Query("api_key") api_key: String, @Query("q") searchInput: String): GiphyResultList

}