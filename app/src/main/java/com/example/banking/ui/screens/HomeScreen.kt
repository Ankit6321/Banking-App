package com.example.banking.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.CardGiftcard
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.LocalOffer
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material.icons.rounded.Score
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.ui.component.BillsSection
import com.example.banking.ui.component.BottomNavigationBar
import com.example.banking.ui.component.BusinessesSection
import com.example.banking.ui.component.MainActions
import com.example.banking.ui.component.MiniActions
import com.example.banking.ui.component.OffersSection
import com.example.banking.ui.component.PeopleSection
import com.example.banking.ui.component.TopSearchBar
import com.example.banking.ui.component.TransactionHistorySection
import com.example.banking.ui.utils.SectionHeader
import com.example.banking.ui.theme.BankingTheme

@Preview
@Composable
private fun LightPreview() {
    BankingTheme {
        HomeScreen()
    }
}

@Preview
@Composable
private fun DarkPreview() {
    BankingTheme(darkTheme = true) {
        HomeScreen()
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier, onPersonClick: (String) -> Unit = {}) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            item { TopSearchBar() }
            item { MainActions() }
            item { MiniActions() }
            item { SectionHeader("People") }
            item { PeopleSection(onPersonClick = onPersonClick) }
            item { SectionHeader("Bills & recharges", "Manage") }
            item { BillsSection() }
            item { SectionHeader("Businesses", "Explore") }
            item { BusinessesSection() }
            item { SectionHeader("Offers & rewards") }
            item { OffersSection() }
            item { TransactionHistorySection() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}
