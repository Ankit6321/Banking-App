package com.example.banking.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.ui.model.Finance
import com.example.banking.ui.theme.BlueStart
import com.example.banking.ui.theme.GreenStart
import com.example.banking.ui.theme.OrangeStart
import com.example.banking.ui.theme.PurpleStart

val finance = listOf<Finance>(
    Finance(
        icon = Icons.Rounded.StarHalf,
        name = "My\nBusiness",
        background = OrangeStart
    ),
    Finance(
        icon = Icons.Rounded.Wallet,
        name = "My\nWallet",
        background = BlueStart
    ),Finance(
        icon = Icons.Rounded.StarHalf,
        name = "Finance\nAnalytics",
        background = PurpleStart
    ),Finance(
        icon = Icons.Rounded.MonetizationOn,
        name = "My\nTransactions",
        background = GreenStart
    ),
)

@Preview
@Composable
fun FinanceSection() {
    Column {
        Text(
            text = "Finance",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }

    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(finance) {
            FinanceItem(it)
        }
    }

}

@Composable
fun FinanceItem(financeItem: Finance, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .size(120.dp)
            .clickable {}
            .padding(13.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(financeItem.background)
                .padding(6.dp),
        ) {
            Icon(
                imageVector = financeItem.icon,
                contentDescription = financeItem.name,
                tint = Color.White
            )
        }

        Text(
            text = financeItem.name,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }

}












