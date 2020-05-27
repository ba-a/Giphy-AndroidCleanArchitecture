package de.giphy_clean_architecture.presentation.search

import de.giphy_clean_architecture.domain.model.Giphy
import io.uniflow.core.flow.data.UIState

sealed class SearchState : UIState() {
    object Loading : SearchState()
    data class ShowSuccess(val searchResultGiphys: List<Giphy>) : SearchState()
    object ShowError : SearchState()
}
