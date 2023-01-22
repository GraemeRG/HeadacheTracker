package com.sbnl.headachetracker.core

import com.sbnl.headachetracker.repositories.Headache
import com.sbnl.headachetracker.repositories.HeadacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class CurrentHeadacheInfoLoadingUseCase(private val headacheRepo: HeadacheRepository) {

    suspend fun getCurrentHeadacheFlow(): Flow<Headache?> =
        headacheRepo
            .flowOfHeadachesSinceStartOfDay()
            .transform {
                emit(
                    it
                        .lastOrNull()
                        ?.nullIfHeadacheHasCleared()
                )
            }

    suspend fun getCurrentHeadacheId(): Long? =
        getCurrentHeadache()
            ?.dateRecorded
            ?.millis

    private suspend fun getCurrentHeadache(): Headache? =
        headacheRepo
            .getAllHeadachesSinceStartOfDay()
            .lastOrNull()
            ?.nullIfHeadacheHasCleared()

    private fun Headache.nullIfHeadacheHasCleared(): Headache? =
        if (timeCleared != null) null else this
}