package de.giphy_clean_architecture.presentation.inject

import de.giphy_clean_architecture.presentation.trending.GiphyTrendingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {

    //region ViewModel

    viewModel {
        GiphyTrendingViewModel(trendingGiphysUseCase = get())
    }
}