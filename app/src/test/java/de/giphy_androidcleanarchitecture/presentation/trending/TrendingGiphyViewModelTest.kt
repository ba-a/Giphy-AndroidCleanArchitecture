package de.giphy_androidcleanarchitecture.presentation.trending

import de.giphy_androidcleanarchitecture.base.BaseTest
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.ErrorModel
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import de.giphy_clean_architecture.presentation.trending.TrendingGiphyState
import de.giphy_clean_architecture.presentation.trending.TrendingGiphyViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.uniflow.android.test.TestViewObserver
import io.uniflow.android.test.createTestObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TrendingGiphyViewModelTest : BaseTest() {
    private val trendingGiphyUseCase: TrendingGiphysUseCase = mockk(relaxed = true)
    private lateinit var viewModel: TrendingGiphyViewModel
    private lateinit var testObserver: TestViewObserver

    @Before
    fun setUp() {
        viewModel = TrendingGiphyViewModel(trendingGiphyUseCase)
        testObserver = viewModel.createTestObserver()
    }

    @Test
    fun `success getting Giphys shows data`() {
        val giphyData = listOf(Giphy("300", "3mb", "gihpyUrl.com", "300"))
        coEvery { trendingGiphyUseCase.getTrendingGiphys() } returns DataResult.Success(giphyData)

        viewModel.getTrendingGiphys()

        testObserver.assertReceived(
            TrendingGiphyState.Loading,
            TrendingGiphyState.ShowSuccess(giphyData)
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

        testObserver.assertReceived(
            TrendingGiphyState.Loading,
            TrendingGiphyState.ShowError
        )
    }
}
