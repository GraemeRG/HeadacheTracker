package com.sbnl.headachetracker.core

import com.sbnl.headachetracker.repositories.Headache
import com.sbnl.headachetracker.repositories.HeadacheRepository

class CurrentHeadacheInfoLoadingUseCase(private val headacheRepo: HeadacheRepository) {

    suspend fun getCurrentHeadache(): Headache? =
        headacheRepo
            .getAllHeadachesSinceStartOfDay()
            .lastOrNull()
            ?.nullIfHeadacheHasCleared()

    suspend fun getCurrentHeadacheId(): Long? =
        getCurrentHeadache()
            ?.dateRecorded
            ?.millis

    private fun Headache.nullIfHeadacheHasCleared(): Headache? =
        if (timeCleared != null) null else this
}