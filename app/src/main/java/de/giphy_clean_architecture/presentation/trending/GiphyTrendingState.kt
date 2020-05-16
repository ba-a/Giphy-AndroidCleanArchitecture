package de.giphy_clean_architecture.presentation.trending

import de.giphy_clean_architecture.domain.model.Giphy
import io.uniflow.core.flow.data.UIState

sealed class GiphyTrendingState : UIState() {
    data class ShowLoading(val showLoadingDialog: Boolean) : GiphyTrendingState()
    data class ShowSuccess(val trendingGiphys: List<Giphy>) : GiphyTrendingState()
    object ShowError : GiphyTrendingState()
}

