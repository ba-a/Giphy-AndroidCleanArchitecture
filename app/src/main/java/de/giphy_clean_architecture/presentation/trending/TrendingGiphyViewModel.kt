package de.giphy_clean_architecture.presentation.trending

import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

class TrendingGiphyViewModel(
    private val trendingGiphysUseCase: TrendingGiphysUseCase
) : AndroidDataFlow(defaultState = UIState.Empty)  {

    fun getTrendingGiphys() = action {
        setState { TrendingGiphyState.ShowLoading(true) }

        when(val result = trendingGiphysUseCase.getTrendingGiphys()) {
            is DataResult.Success -> setState { TrendingGiphyState.ShowSuccess(result.value) }
            is DataResult.Error -> setState { TrendingGiphyState.ShowError }
        }
    }
}
