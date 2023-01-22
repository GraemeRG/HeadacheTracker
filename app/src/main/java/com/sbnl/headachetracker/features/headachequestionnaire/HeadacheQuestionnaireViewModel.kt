package com.sbnl.headachetracker.features.headachequestionnaire

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbnl.headachetracker.database.headache.HeadacheStartPeriod
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlin.random.Random

class HeadacheQuestionnaireViewModel(private val completedUseCase: HeadacheQuestionnaireCompletedUseCase) :
    ViewModel() {

    val headacheStartPeriod = mutableStateOf<HeadacheStartPeriod?>(null)
    val returnToHomeScreen = mutableStateOf(-1)

    var initialPainLevel: Int = DEFAULT_PAIN_LEVEL

    fun onStartPeriodUpdated(startPeriod: HeadacheStartPeriod) {
        headacheStartPeriod.value = startPeriod
    }

    fun onInitialPainLevelUpdated(newPainLevel: Int) {
        initialPainLevel = newPainLevel
    }

    fun onQuestionnaireComplete() {
        viewModelScope.launch(errorHandler) {
            completedUseCase.saveNewHeadacheReport(buildHeadacheReport())
            navigateBackToHomeScreen()
        }
    }

    private fun buildHeadacheReport() =
        HeadacheReport(
            headacheStartPeriod.value ?: throw NotSetStartPeriodException,
            if(initialPainLevel > -1) initialPainLevel else throw NotSetPainLevelException
        )

    private fun navigateBackToHomeScreen() {
        returnToHomeScreen.value = Random.nextInt(from = 0, until = Int.MAX_VALUE)
    }

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("${HeadacheQuestionnaireViewModel::class.simpleName}","Found exception: $exception")
    }

    companion object {
        const val DEFAULT_PAIN_LEVEL = -1
    }
}

object NotSetStartPeriodException: Exception()
object NotSetPainLevelException: Exception()

data class HeadacheReport(val startPeriod: HeadacheStartPeriod, val initialPainLevel: Int)