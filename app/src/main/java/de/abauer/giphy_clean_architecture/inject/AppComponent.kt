package de.abauer.giphy_clean_architecture.inject

import de.abauer.giphy_clean_architecture.data.inject.dataRemoteModule
import de.abauer.giphy_clean_architecture.domain.inject.domainUseCaseModule
import de.abauer.giphy_clean_architecture.presentation.inject.presentationModule

val appComponent = listOf(
    appModule,
    presentationModule,
    domainUseCaseModule,
    dataRemoteModule
)