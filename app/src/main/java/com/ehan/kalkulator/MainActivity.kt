package com.ehan.kalkulator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
//import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import com.ehan.kalkulator.model.ScreenPhase
import com.ehan.kalkulator.ui.theme.KalkulatorTheme
import com.ehan.kalkulator.ui.CalculatorScreen
import com.ehan.kalkulator.ui.KalkulatorScreen
import com.ehan.kalkulator.ui.MainViewModel
import com.ehan.kalkulator.kalkulator.KalkulatorViewModel

class MainActivity : ComponentActivity() {
    private val viewmodel: MainViewModel by viewModels {
        MainViewModel.provideFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context: Context = LocalContext.current
            val lifecycleOwner = LocalLifecycleOwner.current
            val userPreferences by viewmodel.userPreferences.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
            val statusNotification by viewmodel.statusMessage.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

            KalkulatorTheme(
                darkTheme = userPreferences.isDarkTheme,
                accentColor = userPreferences.accentColor
            ) {
                KalkulatorApp(viewmodel = viewmodel)
            }
        }
    }
}

@Composable
fun KalkulatorApp(
    viewmodel: MainViewModel,
    kalkulatorViewModel: KalkulatorViewModel = viewModel()
) {
    val context: Context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val screenPhase by kalkulatorViewModel.screenPhase.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

    var refreshScreen: Boolean by rememberSaveable { mutableStateOf(false) }

    fun _refreshScreen() {
        refreshScreen = !refreshScreen
    }
    when (screenPhase) {
        ScreenPhase.MAINKALKULATOR -> {
            KalkulatorScreen(
                viewModel = viewmodel,
                onTestScreen = {
                    kalkulatorViewModel.toTestScreen()
                }
            )
        }
        ScreenPhase.TESTSCREEN -> {
            CalculatorScreen()
        }
        else -> {
            Text(text = "else")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KalkulatorScreenPreview() {
    val viewmodel: MainViewModel by viewModels {
        MainViewModel.provideFactory(application)
    }
    val kalkulatorViewModel: KalkulatorViewModel = viewModel()
    
    KalkulatorTheme {
        KalkulatorScreen(
            viewModel = viewmodel,
            onTestScreen = {
                kalkulatorViewModel.toTestScreen()
            }
        )
    }
}
