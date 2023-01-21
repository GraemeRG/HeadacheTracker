package com.sbnl.headachetracker.features.headachequestionnaire

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbnl.headachetracker.database.headache.HeadacheStartPeriod
import com.sbnl.headachetracker.repositories.HeadacheRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class HeadacheQuestionnaireViewModel(val completedUseCase: HeadacheQuestionnaireCompletedUseCase) : ViewModel() {

    val headacheStartPeriod = mutableStateOf<HeadacheStartPeriod?>(null)
    val returnToHomeScreen = mutableStateOf(-1)

    fun onAnswerUpdated(startPeriod: HeadacheStartPeriod) {
        headacheStartPeriod.value = startPeriod
    }

    fun onQuestionnaireComplete() {
        headacheStartPeriod.value?.apply {
            viewModelScope.launch {
                completedUseCase.saveNewHeadacheReport(this@apply)
                navigateBackToHomeScreen()
            }
        }
    }

    private fun navigateBackToHomeScreen() {
        returnToHomeScreen.value = Random.nextInt(from = 0, until = Int.MAX_VALUE)
    }
}

class HeadacheQuestionnaireCompletedUseCase(val headacheRepository: HeadacheRepository) {

    suspend fun saveNewHeadacheReport(startPeriod: HeadacheStartPeriod) {
        headacheRepository.storeHeadacheInfo(startPeriod)
    }
}