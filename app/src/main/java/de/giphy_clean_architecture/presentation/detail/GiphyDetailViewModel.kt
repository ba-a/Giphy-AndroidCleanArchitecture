package de.giphy_clean_architecture.presentation.detail

import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

class GiphyDetailViewModel() : AndroidDataFlow(defaultState = UIState.Empty)  {

    fun onGiphyReceived(giphyUrl: String) = action {
        setState(GiphyDetailState.LoadGiphy(giphyUrl))
    }

    fun onShareButtonClick(giphyUrl: String) = action {
        setState(GiphyDetailState.ShareGiphy(giphyUrl))
    }

}
