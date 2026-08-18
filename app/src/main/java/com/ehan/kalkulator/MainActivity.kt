/**package com.ehan.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ehan.kalkulator.ui.navigation.AppNavigation
import com.ehan.kalkulator.ui.theme.kalkulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val preferences by KalkulatorApplication.instance.preferencesRepository.preferences.collectAsStateWithLifecycle()

            kalkulatorTheme(
                themeMode = preferences.themeMode,
                palette = preferences.palette,
                dynamicColor = preferences.dynamicColor
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}

*/
package com.ehan.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ehan.kalkulator.ui.navigation.AppNavigation
import com.ehan.kalkulator.ui.theme.KalkulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val preferences by KalkulatorApplication.instance.preferencesRepository.preferences.collectAsStateWithLifecycle()
            KalkulatorTheme(
                themeMode = preferences.themeMode,
                palette = preferences.palette,
                dynamicColor = preferences.dynamicColor
            ) {
                KalkulatorScreen()
            }
        }
    }
}

@Composable
fun KalkulatorScreen() {
    var expression by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize() 
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom 
    ) {
        // 🖥️ Layar Ekspresi & Hasil
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = expression, fontSize = 28.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = result, fontSize = 40.sp, style = MaterialTheme.typography.headlineLarge)
        }

        // 🔘 Grid Tombol Kalkulator (4x4)
        val buttons = listOf(
            listOf("C", "(", ")", "÷"),
            listOf("7", "8", "9", "×"),
            listOf("4", "5", "6", "-"),
            listOf("1", "2", "3", "+"),
            listOf("0", ".", "⌫", "=")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { symbol ->
                    Button(
                        onClick = {
                            when (symbol) {
                                "C" -> {
                                    expression = ""
                                    result = ""
                                }
                                "⌫" -> {
                                    if (expression.isNotEmpty()) {
                                        expression = expression.dropLast(1)
                                    }
                                }
                                "=" -> {
                                    result = calculateResult(expression)
                                }
                                else -> {
                                    expression += symbol
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp)
                    ) {
                        Text(text = symbol, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

// 🧮 Fungsi Evaluasi Matematika Sederhana
fun calculateResult(expr: String): String {
    return try {
        if (expr.isEmpty()) return ""
        val sanitized = expr.replace("×", "*").replace("÷", "/")
        
        // Pemisahan angka dan operator
        val tokens = mutableListOf<String>()
        var numberBuffer = ""
        for (char in sanitized) {
            if (char in "+-*/") {
                if (numberBuffer.isNotEmpty()) {
                    tokens.add(numberBuffer)
                    numberBuffer = ""
                }
                tokens.add(char.toString())
            } else {
                numberBuffer += char
            }
        }
        if (numberBuffer.isNotEmpty()) tokens.add(numberBuffer)

        // Tahap 1: Perkalian & Pembagian
        var i = 0
        while (i < tokens.size) {
            if (tokens[i] == "*" || tokens[i] == "/") {
                val left = tokens[i - 1].toDouble()
                val right = tokens[i + 1].toDouble()
                val res = if (tokens[i] == "*") left * right else left / right
                tokens[i - 1] = res.toString()
                tokens.removeAt(i)
                tokens.removeAt(i)
                i--
            }
            i++
        }

        // Tahap 2: Penjumlahan & Pengurangan
        var total = tokens[0].toDouble()
        i = 1
        while (i < tokens.size) {
            val op = tokens[i]
            val nextVal = tokens[i + 1].toDouble()
            total = if (op == "+") total + nextVal else total - nextVal
            i += 2
        }

        if (total % 1.0 == 0.0) total.toLong().toString() else total.toString()
    } catch (e: Exception) {
        "Error"
    }
}