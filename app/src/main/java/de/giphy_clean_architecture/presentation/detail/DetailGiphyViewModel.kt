package de.giphy_clean_architecture.presentation.detail

import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

class DetailGiphyViewModel() : AndroidDataFlow(defaultState = UIState.Empty)  {

    fun onGiphyReceived(giphyUrl: String) = action {
        setState(DetailGiphyState.LoadGiphy(giphyUrl))
    }

    fun onShareButtonClick(giphyUrl: String) = action {
        setState(DetailGiphyState.ShareGiphy(giphyUrl))
    }

}
