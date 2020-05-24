package de.giphy_androidcleanarchitecture.presentation.trending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import de.giphy_androidcleanarchitecture.FixedTestViewObserver
import de.giphy_androidcleanarchitecture.createTestObserver
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.ErrorModel
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import de.giphy_clean_architecture.presentation.trending.TrendingGiphyState
import de.giphy_clean_architecture.presentation.trending.TrendingGiphyViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.uniflow.android.test.SimpleObserver
import io.uniflow.android.test.TestViewObserver
import io.uniflow.core.flow.data.UIState
import io.uniflow.core.logger.SimpleMessageLogger
import io.uniflow.core.logger.UniFlowLogger
import io.uniflow.core.logger.UniFlowLoggerTestRule
import io.uniflow.test.rule.TestDispatchersRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TrendingGiphyViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testDispatchersRule = TestDispatchersRule()

    val trendingGiphyUseCase : TrendingGiphysUseCase = mockk(relaxed = true)
    lateinit var viewModel : TrendingGiphyViewModel
    lateinit var testObserver: FixedTestViewObserver

    @Before
    fun before() {
        // create WeatherDataFlow instance with mocked WeatherRepository
        viewModel = TrendingGiphyViewModel(trendingGiphyUseCase)
        // create test observer
        testObserver = viewModel.createTestObserver()
    }

    @Test
    fun `success getting Giphys shows data`() {
        val giphyData = listOf(Giphy("300", "3mb", "gihpyUrl.com", "300"))
        coEvery { trendingGiphyUseCase.getTrendingGiphys() } returns DataResult.Success(giphyData)

        viewModel.getTrendingGiphys()

        testObserver.assertReceived (
            TrendingGiphyState.Loading,
            TrendingGiphyState.ShowSuccess(giphyData)
        )
    }

    @Test
    fun `error getting Giphys show error`() {
        coEvery { trendingGiphyUseCase.getTrendingGiphys() } returns DataResult.Error(ErrorModel("No data", 404))
        viewModel.getTrendingGiphys()

        testObserver.assertReceived (
            TrendingGiphyState.Loading,
            TrendingGiphyState.ShowError
        )
    }
}
