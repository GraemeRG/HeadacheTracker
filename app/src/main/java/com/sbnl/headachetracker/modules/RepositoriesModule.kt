package com.sbnl.headachetracker.modules

import com.sbnl.headachetracker.repositories.HeadacheRepository
import com.sbnl.headachetracker.repositories.HeadacheRepositoryImpl
import com.sbnl.headachetracker.repositories.MedicationRepository
import com.sbnl.headachetracker.repositories.MedicationRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {
    factory<HeadacheRepository> {
        HeadacheRepositoryImpl(
            database = get(),
            dateTimeProvider = get()
        )
    }

    factory<MedicationRepository> {
        MedicationRepositoryImpl(
            database = get()
        )
    }
}