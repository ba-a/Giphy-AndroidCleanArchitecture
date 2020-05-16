package de.giphy_clean_architecture.inject

import de.giphy_clean_architecture.data.dataRemoteModule
import de.giphy_clean_architecture.domain.inject.domainUseCaseModule
import de.giphy_clean_architecture.presentation.inject.presentationModule

val appComponent = listOf(
    presentationModule,
    domainUseCaseModule,
    dataRemoteModule
)