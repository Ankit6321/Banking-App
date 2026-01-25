package com.example.banking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.banking.ui.HomeScreen
import com.example.banking.ui.theme.BankingTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankingTheme {
                HomeScreen()
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color, modifier: Modifier = Modifier) {
        val systemUiController = rememberSystemUiController()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}


