package com.example.banking.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.banking.ui.model.PaymentDetail
import com.example.banking.ui.model.PaymentDirection
import com.example.banking.ui.model.PaymentStatus
import com.example.banking.ui.model.Person

val peopleList = listOf(
    Person("Abhinav", PaymentDetail("500", "12 Oct 2026", "10:30 AM", PaymentStatus.PENDING, PaymentDirection.TO)),
    Person("Priyanshu", PaymentDetail("1200", "11 Oct 2026", "02:15 PM", PaymentStatus.SUCCESS, PaymentDirection.FROM)),
    Person("Eshan", PaymentDetail("250", "10 Oct 2026", "09:45 AM", PaymentStatus.FAILED, PaymentDirection.TO)),
    Person("Ashok", PaymentDetail("3000", "09 Oct 2026", "06:00 PM", PaymentStatus.PENDING, PaymentDirection.TO)),
    Person("Rajib", PaymentDetail("150", "08 Oct 2026", "11:20 AM", PaymentStatus.SUCCESS, PaymentDirection.FROM)),
    Person("Bishal", PaymentDetail("450", "07 Oct 2026", "01:10 PM", PaymentStatus.SUCCESS, PaymentDirection.TO)),
    Person("Odisse", PaymentDetail("900", "06 Oct 2026", "12:00 PM", PaymentStatus.SUCCESS, PaymentDirection.FROM)),
    Person("Sagar", PaymentDetail("100", "05 Oct 2026", "04:30 PM", PaymentStatus.FAILED, PaymentDirection.TO)),
    Person("Ankit", PaymentDetail("2000", "04 Oct 2026", "08:15 AM", PaymentStatus.SUCCESS, PaymentDirection.TO)),
    Person("Rahul", PaymentDetail("750", "03 Oct 2026", "03:50 PM", PaymentStatus.SUCCESS, PaymentDirection.FROM))
)

@Composable
fun PeopleSection(onPersonClick: (String) -> Unit = {}) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .animateContentSize()
    ) {
        // Row 1: Always visible. Contains 4 items.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // First 3 people are always in the first row
            for (i in 0 until 3) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (i < peopleList.size) {
                        val person = peopleList[i]
                        PersonItem(
                            name = person.name,
                            color = person.color,
                            onClick = { onPersonClick(person.name) }
                        )
                    } else {
                        Spacer(modifier = Modifier.size(56.dp))
                    }
                }
            }
            
            // The 4th slot: either Person 4 (index 3) or the 'More' button
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (isExpanded && peopleList.size > 3) {
                    val person = peopleList[3]
                    PersonItem(
                        name = person.name,
                        color = person.color,
                        onClick = { onPersonClick(person.name) }
                    )
                } else {
                    ToggleMoreItem(isExpanded = false) { isExpanded = true }
                }
            }
        }

        // Additional Row: Shown only when expanded. Contains people 5, 6, 7 and 'Less' button.
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // People 5, 6, 7 (index 4, 5, 6)
                for (i in 4 until 7) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        if (i < peopleList.size) {
                            val person = peopleList[i]
                            PersonItem(
                                name = person.name,
                                color = person.color,
                                onClick = { onPersonClick(person.name) }
                            )
                        } else {
                            Spacer(modifier = Modifier.size(56.dp))
                        }
                    }
                }
                
                // The 'Less' button is in the 4th slot of the second row
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    ToggleMoreItem(isExpanded = true) { isExpanded = false }
                }
            }
        }
    }
}

@Composable
fun PersonItem(name: String, color: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(72.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.take(1).uppercase(),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ToggleMoreItem(isExpanded: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(72.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Show Less" else "Show More",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (isExpanded) "Less" else "More",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}
