package de.giphy_clean_architecture.presentation.detail

import io.uniflow.core.flow.data.UIState

sealed class GiphyDetailState: UIState() {
    data class LoadGiphy(val giphyUrl: String) : GiphyDetailState()
    data class ShareGiphy(val giphyUrl: String): GiphyDetailState()
}
