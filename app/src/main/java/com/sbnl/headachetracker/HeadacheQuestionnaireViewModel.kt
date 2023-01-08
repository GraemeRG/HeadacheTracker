package com.sbnl.headachetracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HeadacheQuestionnaireViewModel: ViewModel() {

    val answerToQuestion = mutableStateOf(0)

    fun onAnswerUpdated(answerId: Int) {
        answerToQuestion.value = answerId
    }
}

enum class HeadacheStartTime {
    AWAKE, DAY, EVENING
}