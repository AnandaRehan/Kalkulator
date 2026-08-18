package com.ehan.kalkulator

import android.app.Application
import com.ehan.kalkulator.data.local.AppDatabase
import com.ehan.kalkulator.data.repository.AppItemRepository
import com.ehan.kalkulator.data.repository.AppItemRepositoryImpl
import com.ehan.kalkulator.data.repository.AppPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class KalkulatorApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val itemRepository: AppItemRepository by lazy { AppItemRepositoryImpl(database.appItemDao()) }
    val preferencesRepository: AppPreferencesRepository by lazy { AppPreferencesRepository() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: KalkulatorApplication
            private set
    }
}
