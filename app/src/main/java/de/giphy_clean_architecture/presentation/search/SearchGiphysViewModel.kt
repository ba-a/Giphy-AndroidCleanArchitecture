package de.giphy_clean_architecture.presentation.search

import de.giphy_clean_architecture.domain.model.DataResult
import de.giphy_clean_architecture.domain.usecase.SearchGiphysUseCase
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

class SearchGiphysViewModel(
    private val searchGiphysUseCase: SearchGiphysUseCase
) : AndroidDataFlow(defaultState = UIState.Empty)  {

    fun onSearchInput(searchInput: String) = action {
        setState(SearchState.Loading)
        when(val result = searchGiphysUseCase.searchGiphysForText(searchInput)) {
            is DataResult.Success -> setState { SearchState.ShowSuccess(result.value) }
            is DataResult.Error -> setState { SearchState.ShowError }
        }
    }

}
