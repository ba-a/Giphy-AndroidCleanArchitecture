package de.giphy_androidcleanarchitecture.domain.usecase

import com.google.common.truth.Truth.assertThat
import de.giphy_clean_architecture.domain.helper.ControlledRunner
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.model.ErrorModel
import de.giphy_clean_architecture.domain.model.Giphy
import de.giphy_clean_architecture.domain.repository.TrendingGiphysRepository
import de.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TrendingGiphysUseCaseTest {

    private val trendingGiphyRepository = mockk<TrendingGiphysRepository>()

    private lateinit var controlledRunner: ControlledRunner<DataResult<List<Giphy>>>
    private lateinit var trendingUseCase: TrendingGiphysUseCase

    @Before
    fun setup() {
        controlledRunner = ControlledRunner()
        trendingUseCase = TrendingGiphysUseCase(trendingGiphyRepository, controlledRunner)
    }

    @Test
    fun `invoke Should return DataResult Success When trendingGiphyRepository returns DataResult Success`() =
        runBlocking {
            val trendingGiphy = mockkClass(Giphy::class)
            val dataResult = DataResult.Success(listOf(trendingGiphy))

            coEvery { trendingGiphyRepository.getTrending() } returns dataResult

            val result = trendingUseCase.getTrendingGiphys()

            coVerify { trendingGiphyRepository.getTrending() }
            assertThat(result).isEqualTo(DataResult.Success(listOf(trendingGiphy)))
        }

    @Test
    fun `invoke Should return DataResult Error When trendingGiphyRepository returns DataResult Error`() =
        runBlocking {
            val errorModel = mockkClass(ErrorModel::class)
            val dataResult = DataResult.Error(errorModel)

            coEvery { trendingGiphyRepository.getTrending() } returns dataResult

            val result = trendingUseCase.getTrendingGiphys()

            coVerify { trendingGiphyRepository.getTrending() }
            assertThat(result).isEqualTo(DataResult.Error(errorModel))
        }
}
