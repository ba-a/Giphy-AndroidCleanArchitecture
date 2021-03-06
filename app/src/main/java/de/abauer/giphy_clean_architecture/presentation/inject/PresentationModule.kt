package de.abauer.giphy_clean_architecture.presentation.inject

import de.abauer.giphy_clean_architecture.presentation.detail.DetailGiphyViewModel
import de.abauer.giphy_clean_architecture.presentation.search.SearchGiphysViewModel
import de.abauer.giphy_clean_architecture.presentation.trending.TrendingGiphysViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {

    //region ViewModel

    viewModel {
        TrendingGiphysViewModel(trendingGiphysUseCase = get())
    }

    viewModel {
        SearchGiphysViewModel(searchGiphysUseCase = get())
    }

    viewModel {
        DetailGiphyViewModel()
    }
}