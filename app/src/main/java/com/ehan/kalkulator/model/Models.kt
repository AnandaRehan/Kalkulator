package com.ehan.kalkulator.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ScreenPhase() : Parcelable {
    SPLASH,
    LOADING,
    MAINMENU,
    SETTINGS,
    TESTSCREEN,
    MAINKALKULATOR
}
