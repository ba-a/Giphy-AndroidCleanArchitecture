package de.abauer.giphy_clean_architecture.domain.inject

import de.abauer.giphy_clean_architecture.domain.helper.ControlledRunner
import de.abauer.giphy_clean_architecture.domain.usecase.SearchGiphysUseCase
import de.abauer.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import org.koin.dsl.module


val domainUseCaseModule = module {

    //region UseCase

    single {
        TrendingGiphysUseCase(
            trendingGiphysRepository = get(),
            controlledRunner = ControlledRunner()
        )
    }

    single {
        SearchGiphysUseCase(
            searchGiphysRepository = get(),
            controlledRunner = ControlledRunner()
        )
    }
}