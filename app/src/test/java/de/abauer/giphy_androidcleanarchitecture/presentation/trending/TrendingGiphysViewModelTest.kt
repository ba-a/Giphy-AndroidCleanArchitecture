package de.abauer.giphy_androidcleanarchitecture.presentation.trending

import de.abauer.giphy_androidcleanarchitecture.base.BaseTest
import de.abauer.giphy_clean_architecture.domain.model.DataResult
import de.abauer.giphy_clean_architecture.domain.model.ErrorModel
import de.abauer.giphy_clean_architecture.domain.model.Giphy
import de.abauer.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import de.abauer.giphy_clean_architecture.presentation.trending.TrendingGiphysState
import de.abauer.giphy_clean_architecture.presentation.trending.TrendingGiphysViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.uniflow.android.test.TestViewObserver
import io.uniflow.android.test.createTestObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TrendingGiphysViewModelTest : BaseTest() {
    private val trendingGiphyUseCase: TrendingGiphysUseCase = mockk(relaxed = true)
    private lateinit var viewModel: TrendingGiphysViewModel
    private lateinit var testObserver: TestViewObserver

    @Before
    fun setUp() {
        viewModel = TrendingGiphysViewModel(trendingGiphyUseCase)
        testObserver = viewModel.createTestObserver()
    }

    @Test
    fun `success getting Giphys shows data`() {
        val giphyData = listOf(Giphy("300", "3mb", "gihpyUrl.com", "300"))
        coEvery { trendingGiphyUseCase.getTrendingGiphys() } returns DataResult.Success(giphyData)

        viewModel.getTrendingGiphys()

        testObserver.verifySequence(
            TrendingGiphysState.Loading,
            TrendingGiphysState.ShowSuccess(giphyData)
        )
    }

    @Test
    fun `error getting Giphys show error`() {
        coEvery { trendingGiphyUseCase.getTrendingGiphys() } returns DataResult.Error(
            ErrorModel(
                "No data",
                404
            )
        )
        viewModel.getTrendingGiphys()

        testObserver.verifySequence(
            TrendingGiphysState.Loading,
            TrendingGiphysState.ShowError
        )
    }
}
