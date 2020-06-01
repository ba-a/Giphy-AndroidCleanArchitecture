package de.abauer.giphy_androidcleanarchitecture.presentation.search

import de.abauer.giphy_androidcleanarchitecture.base.BaseTest
import de.abauer.giphy_clean_architecture.domain.model.DataResult
import de.abauer.giphy_clean_architecture.domain.model.ErrorModel
import de.abauer.giphy_clean_architecture.domain.model.Giphy
import de.abauer.giphy_clean_architecture.domain.usecase.SearchGiphysUseCase
import de.abauer.giphy_clean_architecture.presentation.search.SearchGiphysViewModel
import de.abauer.giphy_clean_architecture.presentation.search.SearchState
import io.mockk.coEvery
import io.mockk.mockk
import io.uniflow.android.test.TestViewObserver
import io.uniflow.android.test.createTestObserver
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchGiphysViewModelTest : BaseTest() {
    private val searchGiphysUseCase: SearchGiphysUseCase = mockk(relaxed = true)
    lateinit var viewModel: SearchGiphysViewModel
    lateinit var testObserver: TestViewObserver

    @Before
    fun setUp() {
        viewModel = SearchGiphysViewModel(searchGiphysUseCase)
        testObserver = viewModel.createTestObserver()
    }

    @Test
    fun `success getting Giphys shows data`() {
        runBlocking {
            val giphyData = listOf(Giphy("300", "3mb", "gihpyUrl.com", "300"))
            coEvery { searchGiphysUseCase.searchGiphysForText(any()) } returns DataResult.Success(
                giphyData
            )

            viewModel.onSearchInput("searchInput")

            testObserver.verifySequence(
                UIState.Empty,
                SearchState.Loading,
                SearchState.ShowSuccess(giphyData)
            )
        }
    }

    @Test
    fun `error getting Giphys show error`() {
        coEvery { searchGiphysUseCase.searchGiphysForText(any()) } returns DataResult.Error(
            ErrorModel("No data", 404)
        )
        viewModel.onSearchInput("searchInput")

        testObserver.verifySequence(
            UIState.Empty,
            SearchState.Loading,
            SearchState.ShowError
        )
    }
}
