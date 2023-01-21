package com.sbnl.headachetracker.features.medicationquestionnaire

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbnl.headachetracker.core.CurrentHeadacheInfoLoadingUseCase
import com.sbnl.headachetracker.repositories.Medication
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val DEFAULT_ANSWER = -1L
const val CUSTOM_ANSWER_ID = 0L

class MedicationQuestionnaireViewModel(
    private val headacheUseCase: CurrentHeadacheInfoLoadingUseCase,
    private val questionnaireUseCase: MedicationQuestionnaireUseCase
) : ViewModel() {

    val medicationTypes = mutableStateOf<List<Medication>>(emptyList())
    val answerSelected = mutableStateOf(DEFAULT_ANSWER)
    val returnToHomeScreen = mutableStateOf(-1)
    val noActiveHeadacheEvent: MutableLiveData<String> = MutableLiveData("")

    var customMedication: String = ""
    var currentHeadacheId: Long? = null

    fun onEnterQuestionnaire() {
        viewModelScope.launch {
            currentHeadacheId = headacheUseCase.getCurrentHeadacheId()
            questionnaireUseCase
                .loadMedicationTypes()
                .apply {
                    medicationTypes.value = this
                }
        }
    }

    fun onAnswerUpdated(id: Long) {
        answerSelected.value = id
    }

    fun onCustomAnswerUpdated(customAnswer: String) {
        customMedication = customAnswer
        if (customAnswer.isNotEmpty()) {
            answerSelected.value = CUSTOM_ANSWER_ID
        }
    }

    fun onQuestionnaireComplete() {
        viewModelScope.launch {
            currentHeadacheId
                ?.apply {
                    val answerId = answerSelected.value
                    when {
                        isDefaultAnswer() && !isCustomAnswer() -> {
                            questionnaireUseCase.addMedicationToHeadache(this, answerId)
                            navigateBackToHomeScreen()
                        }
                        isDefaultAnswer() && isCustomAnswer() -> {
                            questionnaireUseCase.addNewMedicationAndLogAgainstHeadache(
                                this,
                                customMedication
                            )
                            navigateBackToHomeScreen()
                        }
                    }
                }
                ?: noActiveHeadacheEvent.postValue("NoActiveHeadache")
        }
    }

    private fun navigateBackToHomeScreen() {
        returnToHomeScreen.value = Random.nextInt(from = 0, until = Int.MAX_VALUE)
    }

    private fun isDefaultAnswer() = answerSelected.value != DEFAULT_ANSWER

    private fun isCustomAnswer() = answerSelected.value == CUSTOM_ANSWER_ID
}