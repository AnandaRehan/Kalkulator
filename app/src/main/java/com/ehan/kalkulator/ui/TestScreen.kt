package com.ehan.kalkulator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181A1C))
            .padding(12.dp)
    ) {
        // Area Display Hasil Utama
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // Area Keypad
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Kolom Kiri (4 dari 5 bagian)
            Column(
                modifier = Modifier.weight(4f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Baris 1: Fungsi Sains
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("√", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                    CalcButton("π", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                    CalcButton("!", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                    CalcButton("e", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                }
                // Baris 2: Simbol Kiri
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("(", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                    CalcButton(")", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                    CalcButton("%", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                    CalcButton("^", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
                // Baris 3: Angka 7-9 & Bagi
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("7", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("8", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("9", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("÷", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
                // Baris 4: Angka 4-6 & Kali
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("4", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("5", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("6", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("×", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
                // Baris 5: Angka 1-3 & Kurang
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("1", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("2", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("3", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("-", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
                // Baris 6: Angka 0, 00, Desimal & Tambah
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("0", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("00", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton(".", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("+", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
            }

            // Kolom Kanan (1 dari 5 bagian: Tombol Aksi Panjang)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalcButton("▲", Modifier.weight(1f).fillMaxWidth(), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                CalcButton("⌫", Modifier.weight(1f).fillMaxWidth(), bg = Color(0xFF2C3238))
                CalcButton("AC", Modifier.weight(2f).fillMaxWidth(), bg = Color(0xFF4C5861), textColor = Color(0xFFD0D7DD))
                CalcButton("=", Modifier.weight(2f).fillMaxWidth(), bg = Color(0xFFBCCBD5), textColor = Color(0xFF1E2328))
            }
        }
    }
}

@Composable
fun CalcButton(
    text: String,
    modifier: Modifier = Modifier,
    bg: Color,
    textColor: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bg)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal
        )
    }
}