package com.sbnl.headachetracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sbnl.headachetracker.MainScreenState.DEFAULT
import com.sbnl.headachetracker.MainScreenState.HEADACHE_QUESTIONAIRE

class MainViewModel: ViewModel() {

    val screenState = mutableStateOf(DEFAULT)

    fun onRecordAHeadacheTapped() {
        if(screenState.value == DEFAULT) {
            screenState.value = HEADACHE_QUESTIONAIRE
        }
    }

    fun onBottomSheetDismissed() {
        screenState.value = DEFAULT
    }
}

enum class MainScreenState {
    DEFAULT, HEADACHE_QUESTIONAIRE
}