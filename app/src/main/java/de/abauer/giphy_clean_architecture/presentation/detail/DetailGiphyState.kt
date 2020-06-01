package de.abauer.giphy_clean_architecture.presentation.detail

import io.uniflow.core.flow.data.UIState

sealed class DetailGiphyState: UIState() {
    data class LoadGiphy(val giphyUrl: String) : DetailGiphyState()
    data class ShareGiphy(val giphyUrl: String): DetailGiphyState()
}
