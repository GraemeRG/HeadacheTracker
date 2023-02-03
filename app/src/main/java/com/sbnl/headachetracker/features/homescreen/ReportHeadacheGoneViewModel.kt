package com.sbnl.headachetracker.features.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbnl.headachetracker.utils.datetime.DateTimeProvider
import com.sbnl.headachetracker.repositories.HeadacheRepository
import kotlinx.coroutines.launch
import org.joda.time.DateTime

class ReportHeadacheGoneViewModel(private val useCase: ReportHeadacheClearedUseCase) : ViewModel() {

    fun reportHeadacheCleared(headacheId: DateTime) {
        viewModelScope.launch {
            useCase.reportHeadacheCleared(headacheId = headacheId.millis)
        }
    }
}

class ReportHeadacheClearedUseCase(private val dateProvider: DateTimeProvider, private val headacheRepo: HeadacheRepository) {

    suspend fun reportHeadacheCleared(headacheId: Long) {
        headacheRepo.updateHeadacheWithClearedTime(headacheId, dateProvider.nowUtcInMillis())
    }
}