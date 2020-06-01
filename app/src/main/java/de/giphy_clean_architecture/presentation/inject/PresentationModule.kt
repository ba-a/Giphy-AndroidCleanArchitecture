package de.giphy_clean_architecture.presentation.inject

import de.giphy_clean_architecture.presentation.detail.DetailGiphyViewModel
import de.giphy_clean_architecture.presentation.search.SearchGiphyViewModel
import de.giphy_clean_architecture.presentation.trending.TrendingGiphyViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {

    //region ViewModel

    viewModel {
        TrendingGiphyViewModel(trendingGiphysUseCase = get())
    }

    viewModel {
        SearchGiphyViewModel(searchGiphysUseCase = get())
    }

    viewModel {
        DetailGiphyViewModel()
    }
}