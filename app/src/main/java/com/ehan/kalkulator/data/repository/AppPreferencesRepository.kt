package com.ehan.kalkulator.data.repository

import com.ehan.kalkulator.ui.theme.AppColorPalette
import com.ehan.kalkulator.ui.theme.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AppPreferences(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val palette: AppColorPalette = AppColorPalette.POLISH,
    val dynamicColor: Boolean = false,
    val showQuickTips: Boolean = true,
    val compactCardView: Boolean = false,
    val developerModeEnabled: Boolean = true
)

class AppPreferencesRepository {
    private val _preferences = MutableStateFlow(AppPreferences())
    val preferences: StateFlow<AppPreferences> = _preferences.asStateFlow()

    private val prefs: SharedPreferences =
        context.getSharedPreferences("template_settings", Context.MODE_PRIVATE)

    private val _themeMode = MutableStateFlow(getSavedThemeMode())
    val themeMode: StateFlow<String> = _themeMode.asStateFlow()

    companion object {
        const val THEME_SYSTEM = "SYSTEM"
        const val THEME_LIGHT = "LIGHT"
        const val THEME_DARK = "DARK"
        private const val KEY_THEME = "theme_mode"
    }

    private fun getSavedThemeMode(): String {
        return prefs.getString(KEY_THEME, THEME_SYSTEM) ?: THEME_SYSTEM
    }

    fun setThemeMode(mode: String) {
        prefs.edit().putString(KEY_THEME, mode).apply()
        _themeMode.value = mode
    }

    fun setThemeMode(mode: ThemeMode) {
        _preferences.update { it.copy(themeMode = mode) }
    }

    fun setColorPalette(palette: AppColorPalette) {
        _preferences.update { it.copy(palette = palette) }
    }

    fun toggleDynamicColor(enabled: Boolean) {
        _preferences.update { it.copy(dynamicColor = enabled) }
    }

    fun toggleQuickTips(enabled: Boolean) {
        _preferences.update { it.copy(showQuickTips = enabled) }
    }

    fun toggleCompactCardView(enabled: Boolean) {
        _preferences.update { it.copy(compactCardView = enabled) }
    }

    fun toggleDeveloperMode(enabled: Boolean) {
        _preferences.update { it.copy(developerModeEnabled = enabled) }
    }
}
package com.example.template.data.repository

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("template_settings", Context.MODE_PRIVATE)

    private val _themeMode = MutableStateFlow(getSavedThemeMode())
    val themeMode: StateFlow<String> = _themeMode.asStateFlow()

    companion object {
        const val THEME_SYSTEM = "SYSTEM"
        const val THEME_LIGHT = "LIGHT"
        const val THEME_DARK = "DARK"
        private const val KEY_THEME = "key_theme_mode"
    }

    private fun getSavedThemeMode(): String {
        return prefs.getString(KEY_THEME, THEME_SYSTEM) ?: THEME_SYSTEM
    }

    fun setThemeMode(mode: String) {
        prefs.edit().putString(KEY_THEME, mode).apply()
        _themeMode.value = mode
    }
}