package de.giphy_androidcleanarchitecture.presentation.detail

import de.giphy_androidcleanarchitecture.base.BaseTest
import de.giphy_clean_architecture.presentation.detail.DetailGiphyState
import de.giphy_clean_architecture.presentation.detail.DetailGiphyViewModel
import io.uniflow.android.test.TestViewObserver
import io.uniflow.android.test.createTestObserver
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailGiphyViewModelTest : BaseTest(){
    lateinit var giphyViewModel : DetailGiphyViewModel
    lateinit var testObserver: TestViewObserver

    @Before
    fun before() {
        giphyViewModel = DetailGiphyViewModel()
        testObserver = giphyViewModel.createTestObserver()
    }

    @Test
    fun `receiving giphy updates state`() {
        val giphyUrl = "giphyUrl.com"
        giphyViewModel.onGiphyReceived(giphyUrl)

        testObserver.assertReceived (
            UIState.Empty,
            DetailGiphyState.LoadGiphy(giphyUrl)
        )
    }

    @Test
    fun `on share giphy updates state`() {
        val giphyUrl = "giphyUrl.com"

        giphyViewModel.onShareButtonClick(giphyUrl)

        testObserver.assertReceived (
            UIState.Empty,
            DetailGiphyState.ShareGiphy(giphyUrl)
        )
    }
}