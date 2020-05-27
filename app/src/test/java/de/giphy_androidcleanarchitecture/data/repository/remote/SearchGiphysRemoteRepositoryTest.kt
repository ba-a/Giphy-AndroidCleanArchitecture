package de.giphy_androidcleanarchitecture.data.repository.remote

import com.google.common.truth.Truth.assertThat
import de.giphy_clean_architecture.AppDispatchers
import de.giphy_clean_architecture.data.model.GiphyTrends
import de.giphy_clean_architecture.data.repository.remote.SearchGiphysRemoteRepository
import de.giphy_clean_architecture.data.repository.remote.mapper.TrendingGiphyRemoteMapper
import de.giphy_clean_architecture.data.service.ApiErrorHandler
import de.giphy_clean_architecture.data.service.ApiService
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.Giphy
import io.mockk.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class SearchGiphysRemoteRepositoryTest {

    private val appDispatchers = object : AppDispatchers {
        override val main: CoroutineDispatcher = Dispatchers.Unconfined
        override val io: CoroutineDispatcher = Dispatchers.Unconfined
    }

    private val apiService = mockk<ApiService>(relaxUnitFun = true)
    private val responseMapper = mockk<TrendingGiphyRemoteMapper>()
    private val apiErrorHandler = ApiErrorHandler()

    private lateinit var searchGiphysRemoteRepository: SearchGiphysRemoteRepository

    @Before
    fun setup() {
        searchGiphysRemoteRepository = SearchGiphysRemoteRepository(
            "some_key",
            apiService,
            responseMapper,
            appDispatchers,
            apiErrorHandler
        )
    }

    @Test
    fun `searching for giphys should return giphy list`() = runBlocking {
        val giphyResponse = mockkClass(GiphyTrends::class)
        coEvery { apiService.searchForGiphys(any(), any()) } returns giphyResponse

        val giphy = mockkClass(Giphy::class)
        val giphyList = listOf(giphy)
        every { giphy.url } returns "randomUrl.gif"
        every { responseMapper(any()) } returns giphyList

        val result = searchGiphysRemoteRepository.searchForGiphys("searchInput")

        coVerifyOrder {
            apiService.searchForGiphys("some_key","searchInput")
            responseMapper(giphyResponse)
        }
        assertThat((result as DataResult.Success).value).isEqualTo(giphyList)
    }

    @Test
    fun `searching for giphys should return error`() = runBlocking {
        val response =
            Response.error<ResponseBody>(400, ResponseBody.create(null, ""))
        val exception = HttpException(response)

        coEvery { apiService.searchForGiphys(any(), any()) } throws exception

        val result = searchGiphysRemoteRepository.searchForGiphys("searchInput")

        coVerifyOrder {
            apiService.searchForGiphys("some_key","searchInput")
        }
        assertThat((result as DataResult.Error).errorModel!!.code).isEqualTo(400)
    }
}


