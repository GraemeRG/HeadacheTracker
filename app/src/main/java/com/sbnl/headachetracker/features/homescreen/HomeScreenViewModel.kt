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
import com.sbnl.headachetracker.utils.datetime.DateTimeConverter
import com.sbnl.headachetracker.utils.datetime.toDisplayTime
import kotlinx.coroutines.launch
import org.joda.time.DateTime

class HomeScreenViewModel(
    private val currentHeadacheInfoLoadingUseCase: CurrentHeadacheInfoLoadingUseCase,
    private val dateTimeConverter: DateTimeConverter
) : ViewModel() {

    val screenState = mutableStateOf<HomeScreenState>(Loading)

    fun onEnterScreen() {
        viewModelScope.launch {
            currentHeadacheInfoLoadingUseCase
                .getCurrentHeadacheFlow()
                .collect { headache ->
                    screenState.value = Content(
                        recordButtonConfig = headache.recordButtonConfiguration(),
                        currentPainLevel = headache.currentPainLevel()
                    )
                }
        }
    }

    private fun Headache?.recordButtonConfiguration() =
        if (this != null) ReportFinished(dateRecorded)
        else ReportNew

    private fun Headache?.currentPainLevel() =
        if (this != null && this.painLevelOverTime.isNotEmpty()) {
            val painLevel = this.painLevelOverTime.last()
            PainLevelDisplayObject(
                painLevel.painLevel,
                dateTimeConverter
                    .toDateTime(painLevel.timeRecorded)
                    .toDisplayTime()
            )
        } else
            PainLevelDisplayObject(0)
}

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Content(
        val recordButtonConfig: RecordButtonConfiguration,
        val currentPainLevel: PainLevelDisplayObject
    ) : HomeScreenState()
}

sealed class RecordButtonConfiguration {
    object ReportNew : RecordButtonConfiguration()
    data class ReportFinished(val headacheId: DateTime) : RecordButtonConfiguration()
}

data class PainLevelDisplayObject(val numericLevel: Int, val recorded: String? = null)