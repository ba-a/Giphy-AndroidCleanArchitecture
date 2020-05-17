package de.giphy_clean_architecture.presentation.trending

import de.giphy_clean_architecture.domain.model.Giphy
import io.uniflow.core.flow.data.UIState

sealed class TrendingGiphyState : UIState() {
    data class ShowLoading(val showLoadingDialog: Boolean) : TrendingGiphyState()
    data class ShowSuccess(val trendingGiphys: List<Giphy>) : TrendingGiphyState()
    object ShowError : TrendingGiphyState()
}

