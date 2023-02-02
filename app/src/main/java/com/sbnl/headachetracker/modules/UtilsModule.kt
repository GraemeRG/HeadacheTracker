package com.sbnl.headachetracker.modules

import com.sbnl.headachetracker.utils.datetime.DateTimeProvider
import com.sbnl.headachetracker.core.CurrentHeadacheInfoLoadingUseCase
import com.sbnl.headachetracker.utils.datetime.DateTimeConverter
import org.koin.dsl.module

val utilsModule = module {
    factory {
        CurrentHeadacheInfoLoadingUseCase(headacheRepo = get())
    }

    factory { DateTimeConverter() }

    factory { DateTimeProvider() }
}