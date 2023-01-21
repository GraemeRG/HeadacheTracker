package com.sbnl.headachetracker.modules

import com.sbnl.headachetracker.DateTimeProvider
import com.sbnl.headachetracker.core.CurrentHeadacheInfoLoadingUseCase
import org.koin.dsl.module

val utilsModule = module {
    factory {
        CurrentHeadacheInfoLoadingUseCase(headacheRepo = get())
    }

    factory { DateTimeProvider() }
}