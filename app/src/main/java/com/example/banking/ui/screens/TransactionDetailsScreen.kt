package com.example.banking.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.ui.model.PaymentDirection
import com.example.banking.ui.model.PaymentStatus
import com.example.banking.ui.model.Person
import com.example.banking.ui.theme.GreenLight
import java.text.NumberFormat
import java.util.Locale.getDefault


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailsScreen(
    modifier: Modifier = Modifier,
    person: Person,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                actions = {
                    IconButton(onClick = {
                        val shareText = """
                            Transaction Details:
                            ${if (person.lastPayment.paymentDirection == PaymentDirection.TO) "To" else "From"}: ${person.name}
                            Amount: ${person.lastPayment.amount}
                            Status: ${person.lastPayment.status.name}
                            Date: ${person.lastPayment.date} at ${person.lastPayment.time}
                        """.trimIndent()

                        val sendIntent = android.content.Intent().apply {
                            action = android.content.Intent.ACTION_SEND
                            putExtra(android.content.Intent.EXTRA_TEXT, shareText)
                            type = "text/plain"
                        }

                        val shareIntent = android.content.Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(color = person.color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = person.name.take(1).uppercase(),
                    textAlign = TextAlign.Center,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(16.dp))

            var text: String
            when (person.lastPayment.paymentDirection) {
                PaymentDirection.TO -> {
                    text = "To " + person.name
                }

                PaymentDirection.FROM -> {
                    text = "From " + person.name
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = formatToCurrency(person.lastPayment.amount),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    Modifier.size(24.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                ) {
                    Icon(
                        when (person.lastPayment.status) {
                            PaymentStatus.SUCCESS -> Icons.Default.Check
                            PaymentStatus.FAILED -> Icons.Default.ErrorOutline
                            PaymentStatus.PENDING -> Icons.Default.AccessTime
                        },
                        contentDescription = null,
                        tint = when (person.lastPayment.status) {
                            PaymentStatus.SUCCESS -> GreenLight
                            PaymentStatus.FAILED -> Color.Red
                            PaymentStatus.PENDING -> Color.Yellow
                        },
                        modifier = Modifier.size(12.dp)
                    )
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text = person.lastPayment.status.name.lowercase()
                        .replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(8.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                modifier = Modifier.width(200.dp)
            )

            Spacer(Modifier.height(16.dp))

            text = "${person.lastPayment.date}, ${person.lastPayment.time}"

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


private fun formatToCurrency(amountString: String): String {
    val amount = amountString.toDoubleOrNull() ?: 0.0
    val formatter = NumberFormat.getCurrencyInstance(getDefault())
    return formatter.format(amount)
}
