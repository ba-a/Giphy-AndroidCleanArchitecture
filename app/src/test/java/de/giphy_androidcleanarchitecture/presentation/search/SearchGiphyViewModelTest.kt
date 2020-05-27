package de.giphy_androidcleanarchitecture.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import de.giphy_androidcleanarchitecture.FixedTestViewObserver
import de.giphy_androidcleanarchitecture.createTestObserver
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.ErrorModel
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.usecase.SearchGiphysUseCase
import de.giphy_clean_architecture.presentation.search.SearchGiphyViewModel
import de.giphy_clean_architecture.presentation.search.SearchState
import de.giphy_clean_architecture.presentation.trending.TrendingGiphyState
import io.mockk.coEvery
import io.mockk.mockk
import io.uniflow.core.flow.data.UIState
import io.uniflow.test.rule.TestDispatchersRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class SearchGiphyViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testDispatchersRule = TestDispatchersRule()

    val searchGiphysUseCase : SearchGiphysUseCase = mockk(relaxed = true)
    lateinit var viewModel : SearchGiphyViewModel
    lateinit var testObserver: FixedTestViewObserver

    @Before
    fun before() {
        // create WeatherDataFlow instance with mocked WeatherRepository
        viewModel = SearchGiphyViewModel(searchGiphysUseCase)
        // create test observer
        testObserver = viewModel.createTestObserver()
    }

    @Test
    fun `success getting Giphys shows data`() {
        val giphyData = listOf(Giphy("300", "3mb", "gihpyUrl.com", "300"))
        coEvery { searchGiphysUseCase.searchGiphysForText(anyString()) } returns DataResult.Success(giphyData)

        viewModel.onSearchInput("searchInput")

        testObserver.assertReceived (
            UIState.Empty,
            TrendingGiphyState.Loading,
            TrendingGiphyState.ShowError
        )
    }

    @Test
    fun `error getting Giphys show error`() {
        coEvery { searchGiphysUseCase.searchGiphysForText(anyString()) } returns DataResult.Error(ErrorModel("No data", 404))
        viewModel.onSearchInput("searchInput")

        testObserver.assertReceived (
            UIState.Empty,
            TrendingGiphyState.Loading,
            TrendingGiphyState.ShowError
        )
    }
}
