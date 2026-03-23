package com.example.banking.ui.component

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.ui.theme.DarkGreen
import com.example.banking.ui.theme.Orange

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
    val context = LocalContext.current
    Surface(
        shape = RoundedCornerShape(100),
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .height(48.dp)
            .clickable {
                Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
            },
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