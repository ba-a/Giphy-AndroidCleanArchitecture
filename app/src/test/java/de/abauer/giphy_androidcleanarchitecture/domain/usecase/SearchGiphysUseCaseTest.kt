package de.abauer.giphy_androidcleanarchitecture.domain.usecase

import com.google.common.truth.Truth
import de.abauer.giphy_clean_architecture.domain.helper.ControlledRunner
import de.abauer.giphy_clean_architecture.domain.model.DataResult
import de.abauer.giphy_clean_architecture.domain.model.ErrorModel
import de.abauer.giphy_clean_architecture.domain.model.Giphy
import de.abauer.giphy_clean_architecture.domain.repository.TrendingGiphysRepository
import de.abauer.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchGiphysUseCaseTest {

    private val searchGiphyRepository = mockk<TrendingGiphysRepository>()

    private lateinit var controlledRunner: ControlledRunner<DataResult<List<Giphy>>>
    private lateinit var searchUseCase: TrendingGiphysUseCase

    @Before
    fun setup() {
        controlledRunner = ControlledRunner()
        searchUseCase = TrendingGiphysUseCase(searchGiphyRepository, controlledRunner)
    }

    @Test
    fun `invoke Should return DataResult Success When searchGiphyRepository returns DataResult Success`() =
        runBlocking {
            val searchGiphy = mockkClass(Giphy::class)
            val dataResult = DataResult.Success(listOf(searchGiphy))

            coEvery { searchGiphyRepository.getTrending() } returns dataResult

            val result = searchUseCase.getTrendingGiphys()

            coVerify { searchGiphyRepository.getTrending() }
            Truth.assertThat(result).isEqualTo(DataResult.Success(listOf(searchGiphy)))
        }

    @Test
    fun `invoke Should return DataResult Error When searchGiphyRepository returns DataResult Error`() =
        runBlocking {
            val errorModel = mockkClass(ErrorModel::class)
            val dataResult = DataResult.Error(errorModel)

            coEvery { searchGiphyRepository.getTrending() } returns dataResult

            val result = searchUseCase.getTrendingGiphys()

            coVerify { searchGiphyRepository.getTrending() }
            Truth.assertThat(result).isEqualTo(DataResult.Error(errorModel))
        }
}
