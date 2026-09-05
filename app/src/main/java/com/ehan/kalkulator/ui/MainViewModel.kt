package com.ehan.kalkulator.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ehan.kalkulator.Kalkulator
import com.ehan.kalkulator.data.UserPreferences
import com.ehan.kalkulator.data.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.ehan.kalkulator.ui.theme.ThemeMode

data class UiNotification(
    val id: Long = System.currentTimeMillis(),
    val message: String
)

class MainViewModel(
    application: Application,
    private val repository: UserPreferencesRepository = (application as Kalkulator).userPreferencesRepository
) : AndroidViewModel(application) {

    // Mengambil preferences secara reaktif dari DataStore melalui Flow
    val userPreferences: StateFlow<UserPreferences> = repository.userPreferencesFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserPreferences()
        )

    private val _statusMessage = MutableStateFlow<UiNotification?>(null)
    val statusMessage: StateFlow<UiNotification?> = _statusMessage.asStateFlow()

    fun setUsername(newUsername: String) {
        val trimmed = newUsername.trim()
        if (trimmed.isNotEmpty()) {
            viewModelScope.launch {
                repository.updateUsername(trimmed)
                _statusMessage.value = UiNotification(message = "Nama berhasil disimpan ke DataStore!")
            }
        }
    }

    fun toggleDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            repository.setDarkTheme(enabled)
            _statusMessage.value = UiNotification(
                message = if (enabled) "Mode gelap diaktifkan" else "Mode terang diaktifkan"
            )
        }
    }

    fun toggleNotifications(enabled: Boolean) {
        viewModelScope.launch {
            repository.setNotificationsEnabled(enabled)
            _statusMessage.value = UiNotification(
                message = if (enabled) "Notifikasi diaktifkan" else "Notifikasi dimatikan"
            )
        }
    }

    fun incrementCounter() {
        viewModelScope.launch {
            repository.incrementCounter()
        }
    }

    fun selectAccentColor(color: String) {
        viewModelScope.launch {
            repository.setAccentColor(color)
            _statusMessage.value = UiNotification(message = "Warna aksen diubah ke $color")
        }
    }

    fun resetToDefaults() {
        viewModelScope.launch {
            repository.clearAll()
            _statusMessage.value = UiNotification(message = "DataStore Preferences telah di-reset ke nilai awal")
        }
    }

    fun clearStatusMessage() {
        _statusMessage.value = null
    }

    companion object {
        fun provideFactory(application: Application): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(application) as T
                }
            }
    }
}
