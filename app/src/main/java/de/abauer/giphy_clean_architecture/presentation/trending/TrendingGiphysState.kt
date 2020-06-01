package de.abauer.giphy_clean_architecture.presentation.trending

import de.abauer.giphy_clean_architecture.domain.model.Giphy
import io.uniflow.core.flow.data.UIState

sealed class TrendingGiphysState : UIState() {
    object Loading : TrendingGiphysState()
    data class ShowSuccess(val trendingGiphys: List<Giphy>) : TrendingGiphysState()
    object ShowError : TrendingGiphysState()
}

