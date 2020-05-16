package de.giphy_clean_architecture.inject

import de.giphy_clean_architecture.AppDispatchers
import de.giphy_clean_architecture.AppDispatchersImpl
import org.koin.dsl.module

val appModule = module {

    single<AppDispatchers> { AppDispatchersImpl() }
}