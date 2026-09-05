package com.ehan.kalkulator

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.ehan.kalkulator.ui.KalkulatorScreen
import com.ehan.kalkulator.ui.theme.KalkulatorTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "+land", sdk = [34])
class KalkulatorScreenshotTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun testKalkulatorScreenPreviewScreenshot() {
    composeTestRule.setContent {
      KalkulatorTheme {
        KalkulatorScreenPreview ()
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/kalkulator_screen.png")
  }
}