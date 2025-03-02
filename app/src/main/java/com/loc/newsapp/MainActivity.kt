package com.loc.newsapp

import OnBoardingScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.loc.newsapp.ui.theme.NewsAppTheme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

          NewsAppTheme {
              Box(modifier = Modifier.background(
                  color = MaterialTheme.colorScheme.background
              )) {
                  OnBoardingScreen()
              }

          }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun QuadraticEquationSolver() {
        var a by remember { mutableStateOf("") }
        var b by remember { mutableStateOf("") }
        var c by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }

        Card {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Phương trình tính toán bậc 2:",)
                OutlinedTextField(
                    value = a,
                    onValueChange = { a = it },
                    label = { Text("Nhập a") }
                )
                OutlinedTextField(
                    value = b,
                    onValueChange = { b = it },
                    label = { Text("Nhập b") }
                )
                OutlinedTextField(
                    value = c,
                    onValueChange = { c = it },
                    label = { Text("Nhập c") }
                )
                Button(onClick = {
                    result = solveQuadraticEquation(a, b, c)
                }) {
                    Text("Giải")
                }
                Text(result, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

    fun solveQuadraticEquation(a: String, b: String, c: String): String {
        val aVal = a.toDoubleOrNull()
        val bVal = b.toDoubleOrNull()
        val cVal = c.toDoubleOrNull()

        if (aVal == null || bVal == null || cVal == null) {
            return "Vui lòng nhập số hợp lệ!"
        }

        if (aVal == 0.0) {
            return if (bVal == 0.0) {
                if (cVal == 0.0) "Phương trình vô số nghiệm" else "Phương trình vô nghiệm"
            } else {
                "Nghiệm x = ${-cVal / bVal}"
            }
        }

        val delta = bVal * bVal - 4 * aVal * cVal
        return when {
            delta > 0 -> {
                val x1 = (-bVal + sqrt(delta)) / (2 * aVal)
                val x2 = (-bVal - sqrt(delta)) / (2 * aVal)
                "Nghiệm x1 = $x1, x2 = $x2"
            }
            delta == 0.0 -> {
                val x = -bVal / (2 * aVal)
                "Nghiệm kép x = $x"
            }
            else -> "Phương trình vô nghiệm"
        }
    }

}
