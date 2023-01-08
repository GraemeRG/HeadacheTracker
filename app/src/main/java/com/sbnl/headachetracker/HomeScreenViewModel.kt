package com.sbnl.headachetracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbnl.headachetracker.HomeScreenState.*
import com.sbnl.headachetracker.repositories.HeadacheRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val useCase: HomeScreenDataUseCase) : ViewModel() {

    val screenState = mutableStateOf<HomeScreenState>(Loading)

    fun onEnterScreen() {
        viewModelScope.launch {
            useCase.hasReportedHeadacheToday().apply {
                screenState.value = Content(
                    recordButtonConfig = when (this) {
                        true -> RecordButtonConiguration.REPORT_FINISHED
                        else -> RecordButtonConiguration.REPORT_NEW
                    }
                )
            }
        }
    }
}

class HomeScreenDataUseCase(val headacheRepo: HeadacheRepository) {

    suspend fun hasReportedHeadacheToday(): Boolean =
        headacheRepo.getAllHeadachesSinceStartOfDay().isNotEmpty()
}

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Content(val recordButtonConfig: RecordButtonConiguration) : HomeScreenState()
}

enum class RecordButtonConiguration {
    REPORT_NEW, REPORT_FINISHED
}