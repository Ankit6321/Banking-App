package com.example.banking.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Score
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.ui.theme.BlueConstant

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
                .clickable{}
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
