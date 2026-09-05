package com.ehan.kalkulator.kalkulator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.ehan.kalkulator.model.ScreenPhase

class KalkulatorViewModel(application: Application) : AndroidViewModel(application) {
    val engine = KalkulatorEngine()

    val screenPhase = engine.screenPhase

    fun setScreenPhase(phase: ScreenPhase) {
        engine.setScreenPhase(phase)
    }
    fun toLoadingScreen() {
        engine.toLoadingScreen()
    }
    fun toMainMenu() {
        engine.toMainMenu()
    }
    fun toMainKalkulator() {
        engine.toMainKalkulator()
    }
    fun toTestScreen() {
        engine.toTestScreen()
    }
}