package de.giphy_androidcleanarchitecture.presentation.detail

import de.giphy_androidcleanarchitecture.base.BaseTest
import de.giphy_clean_architecture.presentation.detail.GiphyDetailState
import de.giphy_clean_architecture.presentation.detail.GiphyDetailViewModel
import io.uniflow.android.test.TestViewObserver
import io.uniflow.android.test.createTestObserver
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GiphyDetailViewModelTest : BaseTest(){
    lateinit var viewModel : GiphyDetailViewModel
    lateinit var testObserver: TestViewObserver

    @Before
    fun before() {
        viewModel = GiphyDetailViewModel()
        testObserver = viewModel.createTestObserver()
    }

    @Test
    fun `receiving giphy updates state`() {
        val giphyUrl = "giphyUrl.com"
        viewModel.onGiphyReceived(giphyUrl)

        testObserver.assertReceived (
            UIState.Empty,
            GiphyDetailState.LoadGiphy(giphyUrl)
        )
    }

    @Test
    fun `on share giphy updates state`() {
        val giphyUrl = "giphyUrl.com"

        viewModel.onShareButtonClick(giphyUrl)

        testObserver.assertReceived (
            UIState.Empty,
            GiphyDetailState.ShareGiphy(giphyUrl)
        )
    }
}