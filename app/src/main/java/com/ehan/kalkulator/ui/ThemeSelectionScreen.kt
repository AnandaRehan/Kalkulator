package com.ehan.kalkulator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ehan.kalkulator.ui.theme.ThemeMode

@Composable
fun ThemeSelectionScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentThemeMode by viewModel.themeModeState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Pilih Tema Aplikasi",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Menggunakan entries (direkomendasikan di Kotlin terbaru menggantikan values())
        ThemeMode.entries.forEach { mode ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (currentThemeMode == mode),
                    onClick = { viewModel.setThemeMode(mode) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                
                // Jauh lebih bersih! Langsung memanggil properti label dari enum 👈
                Text(
                    text = mode.label, 
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}