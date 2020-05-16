package de.giphy_clean_architecture.domain.inject

import de.giphy_clean_architecture.data.helper.ControlledRunner
import de.giphy_clean_architecture.domain.usecase.TrendingGiphysUseCase
import org.koin.dsl.module


val domainUseCaseModule = module {

    //region UseCase

    single {
        TrendingGiphysUseCase(
            giphyTrendingRepository = get(),
            controlledRunner = ControlledRunner()
        )
    }
}