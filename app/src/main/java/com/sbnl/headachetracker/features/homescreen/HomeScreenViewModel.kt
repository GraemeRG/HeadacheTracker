package com.sbnl.headachetracker.features.homescreen

import android.provider.ContactsContract.CommonDataKinds.Identity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbnl.headachetracker.features.homescreen.HomeScreenState.*
import com.sbnl.headachetracker.features.homescreen.RecordButtonConfiguration.*
import com.sbnl.headachetracker.repositories.Headache
import com.sbnl.headachetracker.repositories.HeadacheRepository
import kotlinx.coroutines.launch
import org.joda.time.DateTime

class HomeScreenViewModel(private val useCase: HomeScreenDataUseCase) : ViewModel() {

    val screenState = mutableStateOf<HomeScreenState>(Loading)

    fun onEnterScreen() {
        viewModelScope.launch {
            useCase
                .hasReportedHeadacheToday()
                .apply {
                    screenState.value = Content(
                        recordButtonConfig = recordButtonConfiguration()
                    )
                }
        }
    }

    private fun Headache?.recordButtonConfiguration() =
        if (this != null) ReportFinished(this.dateRecorded)
        else ReportNew
}

class HomeScreenDataUseCase(private val headacheRepo: HeadacheRepository) {

    suspend fun hasReportedHeadacheToday(): Headache? =
        headacheRepo
            .getAllHeadachesSinceStartOfDay()
            .lastOrNull()
            ?.nullIfHeadacheHasCleared()

    private fun Headache.nullIfHeadacheHasCleared(): Headache? =
        if (timeCleared != null) null else this
}

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Content(val recordButtonConfig: RecordButtonConfiguration) : HomeScreenState()
}

sealed class RecordButtonConfiguration {
    object ReportNew : RecordButtonConfiguration()
    data class ReportFinished(val headacheId: DateTime) : RecordButtonConfiguration()
}