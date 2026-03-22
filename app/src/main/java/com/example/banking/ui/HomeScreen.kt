package com.example.banking.ui

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.CardGiftcard
import androidx.compose.material.icons.rounded.CurrencyRupee
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.LocalOffer
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material.icons.rounded.Score
import androidx.compose.material.icons.rounded.SettingsCell
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.ui.theme.BankingTheme
import com.example.banking.ui.theme.BlueConstant
import com.example.banking.ui.theme.BlueLight
import com.example.banking.ui.theme.DarkGreen
import com.example.banking.ui.theme.Orange
import kotlinx.coroutines.delay

@Preview
@Composable
private fun LightPreview() {
    BankingTheme() {
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
fun HomeScreen(modifier: Modifier = Modifier) {
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
            item { PeopleSection() }
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

@Composable
fun TopSearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val placeholders = listOf<String>(
            "Pay by name or phone number",
            "Search for bills",
            "Pay any QR code",
            "Recharge your mobile"
        )

        var currentIndex by rememberSaveable { mutableStateOf(0) }
        var searchText by rememberSaveable { mutableStateOf<String>("") }

        LaunchedEffect(Unit) {
            while (true) {
                delay(3000)
                currentIndex = (currentIndex + 1) % placeholders.size
            }
        }

        Surface(
            modifier = Modifier
                .weight(1f)
                .height(52.dp),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 6.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = {
                        AnimatedContent(
                            targetState = placeholders[currentIndex],
                            transitionSpec = {
                                fadeIn(animationSpec = tween(800)) togetherWith
                                        fadeOut(animationSpec = tween(800))
                            },
                            label = "PlaceholderAnimation"
                        ) { targetPlaceholder ->
                            Text(
                                text = targetPlaceholder,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                fontSize = 14.sp,
                                maxLines = 1
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            Log.d("Testing", "$searchText")
                        }
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF00ACC1)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun MainActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MainActionItem(Icons.Rounded.QrCodeScanner, "Scan any\nQR code")
        MainActionItem(Icons.Rounded.CurrencyRupee, "Pay\nanyone")
        MainActionItem(Icons.Rounded.AccountBalance, "Bank\ntransfer")
        MainActionItem(Icons.Rounded.SettingsCell, "Mobile\nrecharge")
    }
}

@Composable
fun MainActionItem(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

@Composable
fun MiniActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MiniActionItem(Icons.Rounded.Bolt, "UPI Lite", Color.Blue)
        MiniActionItem(Icons.Rounded.EmojiEvents, "Rewards", Orange)
        MiniActionItem(Icons.Rounded.History, "Recent", DarkGreen)
    }
}

@Composable
fun MiniActionItem(icon: ImageVector, label: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(100),
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.height(48.dp),
        border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(label, color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp)
        }
    }
}

@Composable
fun SectionHeader(title: String, action: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        if (action != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(action, color = BlueConstant, fontSize = 14.sp)
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = BlueConstant,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun PeopleSection() {
    val people =
        listOf("Abhinav", "Priyanshu", "Eshan", "ASHOK D..", "Rajib", "Bishal Haz...", "Odisse")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(people) { name ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(name.take(1), color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text("More", color = MaterialTheme.colorScheme.onBackground, fontSize = 11.sp)
            }
        }
    }
}

@Composable
fun BillsSection() {
    val bills = listOf(
        Icons.Rounded.PhoneIphone to "Mobile\nrecharge",
        Icons.Rounded.Tv to "DTH / Cable\nTV",
        Icons.Rounded.Lightbulb to "Electricity",
        Icons.Rounded.WaterDrop to "Water"
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        bills.forEach { (icon, label) ->
            BillItem(icon, label)
        }
    }
}

@Composable
fun BillItem(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(70.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}

@Composable
fun BusinessesSection() {
    val businesses = listOf("BBL Enter..", "SATYAM M...", "Mrs PRIYA...")
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(businesses) { name ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.Magenta),
                    contentAlignment = Alignment.Center
                ) {
                    Text(name.take(1), color = Color.White)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun OffersSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OfferItem(Icons.Rounded.CardGiftcard, "Rewards", Color(0xFFFBC02D))
        OfferItem(Icons.Rounded.LocalOffer, "Offers", Color(0xFFE91E63))
        OfferItem(Icons.Rounded.Share, "Referrals", Color(0xFF2196F3))
    }
}

@Composable
fun OfferItem(icon: ImageVector, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp)
    }
}

@Composable
fun TransactionHistorySection() {
    Column(modifier = Modifier.padding(16.dp)) {
        TransactionRow(Icons.Rounded.Score, "Check your CIBIL score for free")
        TransactionRow(Icons.Rounded.History, "See transaction history")
        TransactionRow(Icons.Rounded.AccountBalance, "Check bank balance")
    }
}

@Composable
fun TransactionRow(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = BlueConstant,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            title,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}
