import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen() {
    // State untuk mengontrol mode tampilan (Expanded / Collapsed)
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181A1C))
            .padding(12.dp)
    ) {
        // Area Display
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
            // Kolom Kiri (Tombol Fungsi & Angka)
            Column(
                modifier = Modifier.weight(4f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isExpanded) {
                    // --- MODE SAINS LENGKAP (3 Baris Atas) ---
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CalcButton("sin", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("cos", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("tan", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("log", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CalcButton("ln", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("e", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("i", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("x", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CalcButton("√", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("∛", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("π", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("!", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                    }
                } else {
                    // --- MODE SEDERHANA (1 Baris Atas) ---
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CalcButton("√", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("π", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("!", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                        CalcButton("e", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                    }
                }

                // --- TOMBOL ANGKA DAN OPERATOR UTAMA ---
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("(", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                    CalcButton(")", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                    CalcButton("%", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                    CalcButton("^", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("7", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("8", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("9", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("÷", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("4", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("5", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("6", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("×", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("1", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("2", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("3", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("-", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("0", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("00", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton(".", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF202427))
                    CalcButton("+", Modifier.weight(1f).aspectRatio(1.1f), bg = Color(0xFF2C3238))
                }
            }

            // Kolom Kanan (Tombol Aksi & Toggle)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalcButton("•", Modifier.weight(1f).fillMaxWidth(), bg = Color(0xFF8399A6), textColor = Color(0xFF1E2328))
                
                // TOMBOL TOGGLE PANAH
                CalcButton(
                    text = if (isExpanded) "▼" else "▲",
                    modifier = Modifier
                        .weight(if (isExpanded) 2f else 1f)
                        .fillMaxWidth(),
                    bg = Color(0xFF8399A6),
                    textColor = Color(0xFF1E2328),
                    onClick = { isExpanded = !isExpanded } // Mengubah status saat diklik
                )

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
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
