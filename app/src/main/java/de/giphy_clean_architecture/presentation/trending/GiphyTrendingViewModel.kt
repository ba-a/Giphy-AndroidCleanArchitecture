package de.giphy_clean_architecture.presentation.trending

import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

class GiphyTrendingViewModel(
    private val trendingGiphysUseCase: TrendingGiphysUseCase
) : AndroidDataFlow(defaultState = UIState.Empty)  {

    fun getTrendingGiphys() = action {
        setState { GiphyTrendingState.ShowLoading(true) }

        when(val result = trendingGiphysUseCase.invoke()) {
            is DataResult.Success -> setState { GiphyTrendingState.ShowSuccess(result.value) }
            is DataResult.Error -> setState { GiphyTrendingState.ShowError }
        }

        setState { GiphyTrendingState.ShowLoading(false) }
    }
}
