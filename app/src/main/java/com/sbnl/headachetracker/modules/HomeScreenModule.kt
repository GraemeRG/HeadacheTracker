package com.sbnl.headachetracker.modules

import com.sbnl.headachetracker.features.homescreen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeScreenModule = module {
    viewModel { HomeScreenViewModel(currentHeadacheInfoLoadingUseCase = get()) }
}