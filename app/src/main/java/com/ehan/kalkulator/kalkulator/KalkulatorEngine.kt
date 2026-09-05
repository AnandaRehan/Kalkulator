package com.ehan.kalkulator.kalkulator

import android.os.Parcelable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.parcelize.Parcelize
import com.ehan.kalkulator.model.ScreenPhase

@Parcelize
class KalkulatorEngine() : Parcelable {
    private val _screenPhase = MutableStateFlow<ScreenPhase>(ScreenPhase.MAINKALKULATOR)
    val screenPhase: StateFlow<ScreenPhase> = _screenPhase.asStateFlow()

    fun setScreenPhase(phase: ScreenPhase) {
        _screenPhase.value = phase
    }
    fun toLoadingScreen() {
        setScreenPhase(ScreenPhase.LOADING)
    }
    fun toMainMenu() {
        setScreenPhase(ScreenPhase.MAINMENU)
    }
    fun toMainKalkulator() {
        setScreenPhase(ScreenPhase.MAINKALKULATOR)
    }
    fun toTestScreen() {
        setScreenPhase(ScreenPhase.TESTSCREEN)
    }
}