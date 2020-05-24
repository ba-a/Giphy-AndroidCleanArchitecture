package de.giphy_clean_architecture.inject

import de.giphy_clean_architecture.data.inject.dataRemoteModule
import de.giphy_clean_architecture.domain.inject.domainUseCaseModule
import de.giphy_clean_architecture.presentation.inject.presentationModule

val appComponent = listOf(
    appModule,
    presentationModule,
    domainUseCaseModule,
    dataRemoteModule
)