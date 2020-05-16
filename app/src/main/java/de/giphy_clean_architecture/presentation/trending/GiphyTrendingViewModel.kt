package de.giphy_clean_architecture.presentation.trending

import de.giphy_clean_architecture.data.model.GiphyTrends
import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

data class GiphyTrendingState(val trendingGiphys : GiphyTrends) : UIState()

class GiphyTrendingViewModel(
    private val trendingGiphysUseCase: TrendingGiphysUseCase
) : AndroidDataFlow(defaultState = UIState.Empty)  {

    fun getTrendingGiphys() = action {

        when(val trendingGiphys = trendingGiphysUseCase.invoke()) {
            is DataResult.Success ->
                setState { GiphyTrendingState(trendingGiphys.value) }
            is DataResult.Error -> {
                // TODO
            }
        }
    }
}
