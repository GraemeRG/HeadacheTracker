package com.sbnl.headachetracker.features.homescreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbnl.headachetracker.core.CurrentHeadacheInfoLoadingUseCase
import com.sbnl.headachetracker.repositories.HeadacheRepository
import kotlinx.coroutines.launch

private const val DEFAULT_DISPLAY_STATE = false

class UpdatePainLevelViewModel(
    private val updatePainLevelUseCase: UpdatePainLevelUseCase
) : ViewModel() {

    val displayUpdatePainLevel: MutableState<Boolean> = mutableStateOf(DEFAULT_DISPLAY_STATE)

    fun onUpdatePainLevelTapped() {
        displayUpdatePainLevel.value = true
    }

    fun onDialogDismissed() {
        displayUpdatePainLevel.value = false
    }

    fun onPainLevelUpdated(painLevel: Int) {
        viewModelScope.launch {
            try {
                updatePainLevelUseCase.updatePainLevelForCurrentHeadache(painLevel)
                displayUpdatePainLevel.value = false
            } catch (e: Exception) {
                Log.d("LISTEN!", e.localizedMessage)
            }
        }
    }
}

class UpdatePainLevelUseCase(
    private val currentHeadache: CurrentHeadacheInfoLoadingUseCase,
    private val headacheRepo: HeadacheRepository
) {

    suspend fun updatePainLevelForCurrentHeadache(newLevel: Int) {
        currentHeadache
            .getCurrentHeadacheId()
            ?.apply {
                headacheRepo.updatePainLevelForHeadache(this, newLevel)
            }
            ?: throw Exception("No headache is recorded at the moment")
    }
}