package com.example.banking.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            WalletSection()
            CardSection()
            Spacer(Modifier.height(16.dp))
            FinanceSection()
            CurrencySection()
        }
    }
}