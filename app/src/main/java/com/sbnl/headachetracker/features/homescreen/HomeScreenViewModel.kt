package com.sbnl.headachetracker.features.homescreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbnl.headachetracker.core.CurrentHeadacheInfoLoadingUseCase
import com.sbnl.headachetracker.features.homescreen.HomeScreenState.Content
import com.sbnl.headachetracker.features.homescreen.HomeScreenState.Loading
import com.sbnl.headachetracker.features.homescreen.RecordButtonConfiguration.ReportFinished
import com.sbnl.headachetracker.features.homescreen.RecordButtonConfiguration.ReportNew
import com.sbnl.headachetracker.repositories.Headache
import kotlinx.coroutines.launch
import org.joda.time.DateTime

class HomeScreenViewModel(private val currentHeadacheInfoLoadingUseCase: CurrentHeadacheInfoLoadingUseCase) : ViewModel() {

    val screenState = mutableStateOf<HomeScreenState>(Loading)

    fun onEnterScreen() {
        viewModelScope.launch {
            currentHeadacheInfoLoadingUseCase.getCurrentHeadacheFlow().collect { headache ->
                screenState.value = Content(
                    recordButtonConfig = headache.recordButtonConfiguration()
                )
            }
        }
    }

    private fun Headache?.recordButtonConfiguration() =
        if (this != null) ReportFinished(this.dateRecorded)
        else ReportNew
}

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Content(val recordButtonConfig: RecordButtonConfiguration) : HomeScreenState()
}

sealed class RecordButtonConfiguration {
    object ReportNew : RecordButtonConfiguration()
    data class ReportFinished(val headacheId: DateTime) : RecordButtonConfiguration()
}